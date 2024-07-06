package org.springframework.samples.petclinic.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Owner findById(Integer ownerId) {
        return ownerId == null ? new Owner() : this.ownerRepository.findById(ownerId);
    }

    public void save(Owner owner) {
        this.ownerRepository.save(owner);
    }

    public Page<Owner> findPaginatedByLastName(int page, String lastName, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return ownerRepository.findByLastName(lastName, pageable);
    }
}