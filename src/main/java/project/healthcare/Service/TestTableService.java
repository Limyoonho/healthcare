package project.healthcare.Service;

import project.healthcare.Entity.TestTable;
import project.healthcare.Repository.TestTableRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TestTableService {

    @Autowired
    private TestTableRepository testTableReposity;

    public List<TestTable> findAll(){
        return testTableReposity.findAll();
    }
}
