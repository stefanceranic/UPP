package root.naucnaCentrala.services;

import java.util.List;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import root.naucnaCentrala.model.Casopis;

@Service
public class PaymentService {

	@Autowired
	TaskService taskService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private IdentityService identityService;

	public void checkPay() {
		List<Task> a = taskService.createTaskQuery().active().list();
		String tI = a.get(0).getId();
		Task task = taskService.createTaskQuery().taskId(tI).singleResult();

		String processInstanceId = task.getProcessInstanceId();

		Casopis casopis = (Casopis) runtimeService.getVariable(processInstanceId, "casopis");
		User user = identityService.createUserQuery()
				.userId((String) runtimeService.getVariable(processInstanceId, "ulogovani")).list().get(0);

		runtimeService.setVariable(processInstanceId, "aktivna", false);

		for (int i = 0; i < casopis.getPretplatnici().size(); i++) {
			if(casopis.getPretplatnici().get(i).getUsername().equals(user.getId())) {
				runtimeService.setVariable(processInstanceId, "aktivna", true);
				break;
			}
		}

	}

}
