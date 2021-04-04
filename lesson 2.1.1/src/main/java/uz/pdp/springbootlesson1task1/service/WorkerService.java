package uz.pdp.springbootlesson1task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootlesson1task1.entity.Address;
import uz.pdp.springbootlesson1task1.entity.ApiResponse;
import uz.pdp.springbootlesson1task1.entity.Department;
import uz.pdp.springbootlesson1task1.entity.Worker;
import uz.pdp.springbootlesson1task1.payload.WorkerDto;
import uz.pdp.springbootlesson1task1.repository.AddressRepository;
import uz.pdp.springbootlesson1task1.repository.DepartmentRepository;
import uz.pdp.springbootlesson1task1.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    public List<Worker> get(){
        return  workerRepository.findAll();
    }
    public ApiResponse getById(Integer id){
        Optional<Worker> workerId = workerRepository.findById(id);
        if (!workerId.isPresent()) return new ApiResponse("Worker not found", false, null);
        Worker worker = workerId.get();
        return new ApiResponse("Worker found", true, worker);
    }
    public ApiResponse add(WorkerDto workerDto){
        boolean exists = workerRepository.existsByNameAndPhoneNumber(workerDto.getName(), workerDto.getPhoneNumber());
        if (exists) return new ApiResponse("Worker is already exists", false, null);
        Optional<Department> departmentId = departmentRepository.findById(workerDto.getDepartmentId());
        if (!departmentId.isPresent()) return new ApiResponse("Department not found", false, null);
        Worker worker = new Worker();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setDepartment(departmentId.get());

        Address address = new Address();
        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());
        Address addressSaved = addressRepository.save(address);
        worker.setAddress(addressSaved);
        Worker save = workerRepository.save(worker);
        return new ApiResponse("Worker added", true, save);
    }
    public ApiResponse edit(Integer id, WorkerDto workerDto){
        boolean exists = workerRepository.existsByNameAndPhoneNumberAndIdNot(workerDto.getName(), workerDto.getPhoneNumber(), id);
        if (exists) return new ApiResponse("Worker is already exists", false, null);
        Optional<Worker> workerId = workerRepository.findById(id);
        if (!workerId.isPresent()) return new ApiResponse("Worker not found", false, null);
        Optional<Department> departmentId = departmentRepository.findById(workerDto.getDepartmentId());
        if (!departmentId.isPresent()) return new ApiResponse("Department not found", false, null);

        Worker worker = workerId.get();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setDepartment(departmentId.get());

        Optional<Address> addressId = addressRepository.findById(id);
        if (!addressId.isPresent()) return new ApiResponse("Address not found", false, null);
        Address address = addressId.get();
        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());
        Address addressSaved = addressRepository.save(address);
        worker.setAddress(addressSaved);
        Worker save = workerRepository.save(worker);
        return new ApiResponse("Worker edited", true, save);
    }
    public ApiResponse del(Integer id){
        Optional<Worker> workerId = workerRepository.findById(id);
        if (!workerId.isPresent()) return new ApiResponse("Worker not found", false, null);
        workerRepository.deleteById(id);
        addressRepository.deleteById(id);
        return new ApiResponse("Worker deleted", true, null);
    }
}
