package root.naucnaCentrala.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import root.naucnaCentrala.model.Casopis;
import root.naucnaCentrala.model.FormFieldsDto;
import root.naucnaCentrala.model.FormSubmissionDto;
import root.naucnaCentrala.model.NaucnaOblast;
import root.naucnaCentrala.model.Rad;
import root.naucnaCentrala.model.TipCasopisa;
import root.naucnaCentrala.model.TipUrednika;
import root.naucnaCentrala.model.Urednik;
import root.naucnaCentrala.repository.UrednikRepository;
import root.naucnaCentrala.services.CasopisService;
import root.naucnaCentrala.services.EmailServiceImpl;
import root.naucnaCentrala.services.RadService;

@org.springframework.stereotype.Controller
@RequestMapping("/welcome")
public class Controller {

	@Autowired
	TaskService taskService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	FormService formService;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private CasopisService casopisService;

	@Autowired
	private RadService radService;

	@Autowired
	private EmailServiceImpl emailService;

	@Autowired
	private UrednikRepository urednikRep;

	@GetMapping(path = "/get", produces = "application/json")
	public @ResponseBody FormFieldsDto get() {
		// provera da li korisnik sa id-jem pera postoji
		// List<User> users = identityService.createUserQuery().userId("pera").list();
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("Glavni");
		System.out.println("Poceo glavni proces");

		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		return new FormFieldsDto(task.getId(), pi.getId(), properties);
	}

	@GetMapping(path = "/register/{taskId}", produces = "application/json")
	public @ResponseBody ResponseEntity toRegister(@PathVariable String taskId) {

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();

		runtimeService.setVariable(processInstanceId, "registrovan", false);
		taskService.complete(taskId);
		System.out.println("Zavrsen task login");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/isLoggedIn/{taskId}", produces = "application/json")
	public @ResponseBody boolean isLoggedIn(@PathVariable String taskId) {

		Task task = taskService.createTaskQuery().active().list().get(0);
		String piId = task.getProcessInstanceId();
		User user = identityService.createUserQuery().userId((String) runtimeService.getVariable(piId, "ulogovani"))
				.list().get(0);
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(user.getId()).list();
		if (tasks.isEmpty())
			return false;
		else
			return true;
	}

	@GetMapping(path = "/getRegister", produces = "application/json")
	public @ResponseBody FormFieldsDto getRegister() {
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processDefinitionKey("registracija").active()
				.list().get(0);
		System.out.println("Poceo registracioni proces");

		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();

		return new FormFieldsDto(task.getId(), pi.getId(), properties);
	}

	@GetMapping(path = "/getFF", produces = "application/json")
	public @ResponseBody FormFieldsDto getFieldsF() {

		Task task = taskService.createTaskQuery().active().list().get(0);
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processDefinitionKey("Glavni").active().list()
				.get(0);
		System.out.println(task.getId());
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		System.out.println(tfd.getFormFields().size());
		List<FormField> properties = tfd.getFormFields();

		return new FormFieldsDto(task.getId(), pi.getId(), properties);
	}

	@PostMapping(path = "/post/{taskId}", produces = "application/json")
	public @ResponseBody ResponseEntity tryRegister(@RequestBody List<FormSubmissionDto> dto,
			@PathVariable String taskId) {
		HashMap<String, Object> map = this.mapListToDto(dto);

		List<Task> a = taskService.createTaskQuery().active().list();
		String tI = a.get(0).getId();
		System.out.println(tI);

		Task task = taskService.createTaskQuery().taskId(tI).singleResult();

		System.out.println(taskId);
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "registration", dto);

		formService.submitTaskForm(tI, map);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/postLogin/{taskId}", produces = "application/json")
	public @ResponseBody ResponseEntity tryLogin(@RequestBody List<FormSubmissionDto> dto,
			@PathVariable String taskId) {
		HashMap<String, Object> map = this.mapListToDto(dto);

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();

		runtimeService.setVariable(processInstanceId, "registrovan", true);

		runtimeService.setVariable(processInstanceId, "login", dto);

		formService.submitTaskForm(taskId, map);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/postRad/{taskId}", produces = "application/json")
	public @ResponseBody ResponseEntity prijavaRad(@PathVariable String taskId,
			@RequestBody List<FormSubmissionDto> dto) {
		HashMap<String, Object> map = this.mapListToDto(dto);

		Rad r = new Rad();
		for (FormSubmissionDto f : dto) {
			if (f.getFieldId().equals("naslov")) {
				r.setNaziv(f.getFieldValue());
			}
			if (f.getFieldId().equals("apstrakt")) {
				r.setApstrakt(f.getFieldValue());
			}
			if (f.getFieldId().equals("naucnaOblast")) {
				if (f.getFieldValue().equals(NaucnaOblast.Biologija)) {
					r.setNaucnaOblast(NaucnaOblast.Biologija);
				}
				if (f.getFieldValue().equals(NaucnaOblast.Fizika)) {
					r.setNaucnaOblast(NaucnaOblast.Fizika);
				}
				if (f.getFieldValue().equals(NaucnaOblast.Hemija)) {
					r.setNaucnaOblast(NaucnaOblast.Hemija);
				}
				if (f.getFieldValue().equals(NaucnaOblast.Matematika)) {
					r.setNaucnaOblast(NaucnaOblast.Matematika);
				}

			}
		}

		radService.saveRad(r);

//        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
//        String processInstanceId = task.getProcessInstanceId();
//        List<String> mejlovi = new ArrayList<String>();
//        mejlovi.add("uvrnuto@gmail.com");
//        mejlovi.add("stefanr.ceranic@gmail.com");
//        runtimeService.setVariable(processInstanceId, "mejlovi", mejlovi);
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "rad", r);

		taskService.complete(taskId);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/odabraniCasopis/{id}", produces = "application/json")
	public @ResponseBody ResponseEntity odabraniCasopis(@PathVariable String id, @RequestBody String odabrani) {
		System.out.println(odabrani);
		Long idd = Long.valueOf(odabrani);
		System.out.println(idd);
		Task task = taskService.createTaskQuery().taskId(id).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		List<String> mejlovi = new ArrayList<String>();
		Casopis c = casopisService.pronadjiCasopis(idd);
		runtimeService.setVariable(processInstanceId, "casopis", c);
		User user = identityService.createUserQuery()
				.userId((String) runtimeService.getVariable(processInstanceId, "ulogovani")).list().get(0);
		mejlovi.add(user.getEmail());
		for (int i = 0; i < c.getUrednici().size(); i++) {
			if (c.getUrednici().get(i).getTipUrednika().equals(TipUrednika.GLAVNI))
				mejlovi.add(c.getUrednici().get(i).getEmail());
		}
		runtimeService.setVariable(processInstanceId, "mejlovi", mejlovi);
		if (c.getTipCasopisa() == TipCasopisa.nonOpenAcces) {
			runtimeService.setVariable(processInstanceId, "openAccess", false);
			taskService.complete(id);
		} else {
			runtimeService.setVariable(processInstanceId, "openAccess", true);
			taskService.complete(id);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/where/{taskId}", produces = "application/json")
	public @ResponseBody String whereToGo(@PathVariable String taskId) {

		String s = (String) runtimeService.getVariable(taskId, "jedinstven");
		return s;
	}

	private HashMap<String, Object> mapListToDto(List<FormSubmissionDto> list) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (FormSubmissionDto temp : list) {
			map.put(temp.getFieldId(), temp.getFieldValue());
		}

		return map;
	}
}
