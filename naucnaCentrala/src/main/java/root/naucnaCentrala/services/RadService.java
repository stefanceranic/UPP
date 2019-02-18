package root.naucnaCentrala.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.naucnaCentrala.model.Rad;
import root.naucnaCentrala.repository.RadRepository;

@Service
public class RadService {

	@Autowired
	RadRepository radRepository;
	
	public void saveRad(Rad r) {
		radRepository.save(r);
	}
}
