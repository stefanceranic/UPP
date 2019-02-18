package root.naucnaCentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import root.naucnaCentrala.model.NaucnaOblast;
import root.naucnaCentrala.model.TipUrednika;
import root.naucnaCentrala.model.Urednik;

import java.util.List;

public interface UrednikRepository extends JpaRepository<Urednik, Long> {

    public List<Urednik> findByNaucnaOblast(NaucnaOblast n);


}
