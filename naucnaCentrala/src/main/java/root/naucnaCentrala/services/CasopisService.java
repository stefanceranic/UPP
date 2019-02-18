package root.naucnaCentrala.services;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.naucnaCentrala.model.*;
import root.naucnaCentrala.repository.CasopisRepository;
import root.naucnaCentrala.repository.UrednikRepository;

import java.util.List;

@Service
public class CasopisService implements JavaDelegate {

	@Autowired
	CasopisRepository casopisRepository;

	@Autowired
	UrednikRepository urednikRepository;

	@Autowired
	TaskService taskService;

	@Autowired
	RuntimeService runtimeService;



	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
	}

	public Casopis pronadjiCasopis(Long idd) {
		Casopis c = casopisRepository.findById(idd).get();
		return c;
	}

	public void odabirUrednikaNaucne(Rad r, Casopis c){

		Task task = taskService.createTaskQuery().active().list().get(0);
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processDefinitionKey("Glavni").active().list()
				.get(0);
		boolean b = false;

		for (int j = 0; j < c.getUrednici().size(); j++){
			if(c.getUrednici().get(j).getNaucnaOblast() == r.getNaucnaOblast()){
				r.setUrednikNaucne(c.getUrednici().get(j));
				c.getUrednici().get(j).getRadovi().add(r);
				runtimeService.setVariable(pi.getId(), "imaAktivnog", true);
			    b = true;
			}
		}
		if(!b){
			runtimeService.setVariable(pi.getId(), "imaAktivnog", false);
		}
	}
	public void dodeliGlavom(Rad r, Casopis casopis) {
		Task task = taskService.createTaskQuery().active().list().get(0);
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processDefinitionKey("Glavni").active().list()
				.get(0);

		for (int i = 0; i < casopis.getUrednici().size(); i++) {
			if (casopis.getUrednici().get(i).getTipUrednika().equals(TipUrednika.GLAVNI)) {
				r.setUrednikNaucne(casopis.getUrednici().get(i));

			}
		}
	}

		public void dodeljivanjeGlavnom(Rad rad, Casopis casopis){
			Task task = taskService.createTaskQuery().active().list().get(0);
			ProcessInstance pi = runtimeService.createProcessInstanceQuery().processDefinitionKey("Glavni").active().list()
					.get(0);

			for(int i=0; i < casopis.getUrednici().size(); i++){
				if(casopis.getUrednici().get(i).getTipUrednika().equals(TipUrednika.GLAVNI)){
					casopis.getUrednici().get(i).getRadovi().add(rad);

				}
			}

		}

	public List<Recenzent> ponudiRecenzente(Casopis c){
		List<Recenzent> r = c.getRecenzenti();
		return r;

	}


}
