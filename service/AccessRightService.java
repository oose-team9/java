package service;

import java.util.ArrayList;

import domain.AccessRight;
import persistence.AccessRightRepository;

public class AccessRightService {
	private final AccessRightRepository accessRightRepository = AccessRightRepository.getInstacne();
	public AccessRightService() {}
	public void write(AccessRight accessRight) throws Exception {
		accessRightRepository.save(accessRight);
	}
	public ArrayList<AccessRight> findBoards() {
        	return accessRightRepository.findAll();
    }
}
