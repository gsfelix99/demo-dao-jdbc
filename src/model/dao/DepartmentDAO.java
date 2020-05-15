package model.dao;

import model.entities.Departmet;
import java.util.List;

public interface DepartmentDAO {
    void insert(Departmet obj);
    void update(Departmet obj);
    void deleteById(Integer id);
    Departmet findById(Integer id);
    List<Departmet> findAll();
}
