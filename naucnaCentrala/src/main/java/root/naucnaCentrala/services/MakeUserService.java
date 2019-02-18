package root.naucnaCentrala.services;

import java.util.List;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.naucnaCentrala.model.FormSubmissionDto;

@Service
public class MakeUserService implements JavaDelegate {

	@Autowired
	IdentityService identityService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		List<FormSubmissionDto> registration = (List<FormSubmissionDto>) execution.getVariable("registration");
		User user = identityService.newUser("242");
		for (FormSubmissionDto formField : registration) {

			if (formField.getFieldId().equals("username")) {

				user.setId(formField.getFieldValue());
			}
			if (formField.getFieldId().equals("password")) {
				user.setPassword(formField.getFieldValue());
			}
			if (formField.getFieldId().equals("email")) {
				user.setEmail(formField.getFieldValue());
			}
			if (formField.getFieldId().equals("first")) {
				user.setFirstName(formField.getFieldValue());
			}
			if (formField.getFieldId().equals("last")) {
				user.setLastName(formField.getFieldValue());
			}
		}
		identityService.saveUser(user);
	}
}
