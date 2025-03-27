package management.residentmanagement.service;

import management.residentmanagement.entity.Bill;
import management.residentmanagement.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;

    public void save(Bill bill){
        billRepository.save(bill);
    }
}
