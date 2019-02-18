package root.naucnaCentrala.services;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.naucnaCentrala.model.FormSubmissionDto;

import java.util.List;

@Service
public class CheckUniqueService implements JavaDelegate {

	@Autowired
	IdentityService identityService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		List<FormSubmissionDto> registration = (List<FormSubmissionDto>) execution.getVariable("registration");
		for (FormSubmissionDto field : registration) {
			if (field.getFieldId().equals("username")) {
				List<User> users = identityService.createUserQuery().userId(field.getFieldValue()).list();
				if (users.isEmpty()) {
					execution.setVariable("jedinstven", true);
					System.out.println("Jedinstven user: " + field.getFieldValue());
				} else {
					execution.setVariable("jedinstven", false);
					System.out.println("Nije jedinstven user: " + field.getFieldValue());
					System.out.println("sfasfsafsa");
				}
			}
		}

	}
}
