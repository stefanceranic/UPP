package root.naucnaCentrala.services;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.naucnaCentrala.model.Casopis;
import root.naucnaCentrala.model.FormSubmissionDto;
import root.naucnaCentrala.repository.CasopisRepository;

@Service
public class AuthenticationService implements JavaDelegate {

    @Autowired
    IdentityService identityService;

    @Autowired
    CasopisRepository casopisRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<FormSubmissionDto> registration = (List<FormSubmissionDto>) execution.getVariable("login");
        boolean userOk = false;
        boolean passwordOk = false;
        User user = null;
        for (FormSubmissionDto field : registration){
            if (field.getFieldId().equals("username")) {
                List<User> users = identityService.createUserQuery().userId(field.getFieldValue()).list();
                if (!users.isEmpty()) {
                    userOk = true;
                    user = users.get(0);
                }
            }
        }
        for(FormSubmissionDto field : registration){
            if (field.getFieldId().equals("password")){
                List<User> users = identityService.createUserQuery().userId(user.getId()).list();
                if(users.get(0).getLastName().equals(field.getFieldValue()))
                    passwordOk = true;
            }
        }

        if(userOk && passwordOk){
            execution.setVariable("prijavljen", true);
            execution.setVariable("ulogovani", user.getId());
            System.out.println("ulogovan");
            List<Casopis> casopis= casopisRepository.findAll();
            List<String> n = new ArrayList<String>();
            for(int i=0; i< casopis.size();i++){
                System.out.println(casopis.get(i).getNaziv());

                n.add(casopis.get(i).getNaziv());
            }
            execution.setVariable("listaCasopisa", n);
        } else {
            execution.setVariable("prijavljen", false);
            System.out.println("ulogovan nijee");
        }
    }
}
