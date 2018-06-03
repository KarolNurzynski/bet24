package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Operation;
import pl.coderslab.repository.OperationRepository;
import pl.coderslab.serviceInterface.OperationServiceInterface;

import java.util.List;

@Service
public class OperationService implements OperationServiceInterface {

    OperationRepository operationRepository;

    @Autowired
    OperationService(OperationRepository operationRepository) {
        this.operationRepository=operationRepository;
    }

    public List<Operation> findAllOperations() {
        return operationRepository.findAll();
    }

    public Operation findOperationById(Long id) {
        return operationRepository.findById(id).orElseGet(null);
    }

    public Operation saveOperation(Operation operation) {
        return operationRepository.save(operation);
    }

    public Operation editOperation(Operation operation) {
        return operationRepository.save(operation);
    }

    public void deleteOperation(Long id) {
        operationRepository.deleteById(id);
    }

}
