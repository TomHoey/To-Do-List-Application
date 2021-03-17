package com.qa.toDoList.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.toDoList.data.models.ToDoList;
import com.qa.toDoList.data.respository.ToDoListRepository;
import com.qa.toDoList.dto.ToDoListDTO;
import com.qa.toDoList.mappers.ToDoListMapper;

@Service
public class ToDoListService {
	
	private ToDoListRepository tdlRepo;
	
	private ToDoListMapper tdlMapper;
	
	@Autowired
	public ToDoListService(ToDoListRepository tdlRepo, ToDoListMapper tdlMapper) {
		this.tdlRepo = tdlRepo;
		this.tdlMapper = tdlMapper;
	}
	
	@Transactional
	public List<ToDoListDTO> readAllLists() {
		List<ToDoList> listInDB = tdlRepo.findAll();
		List<ToDoListDTO> returned = new ArrayList<ToDoListDTO>();
		listInDB.forEach(list -> {
			returned.add(tdlMapper.mapToDTO(list));
		});
		
		return returned;
	}
	
	public ToDoListDTO createList (ToDoList toDoList) {
		ToDoList newList = tdlRepo.save(toDoList);
		
		return tdlMapper.mapToDTO(newList);
	}
	
	public ToDoListDTO updateList (Integer toDoID, ToDoList toDoList) {
		if (!tdlRepo.existsById(toDoID)) {
			throw new EntityNotFoundException();
		}
		
		Optional<ToDoList> listOpt = tdlRepo.findById(toDoID);
		ToDoList listInDB = listOpt.orElseThrow(() -> {
			throw new EntityNotFoundException();
		});
		
		listInDB.setToDoListName(toDoList.getToDoListName());
		
		return tdlMapper.mapToDTO(tdlRepo.save(listInDB));
	}
	
	public Boolean deleteList (Integer toDoID) {
		if (tdlRepo.existsById(toDoID)) {
			tdlRepo.deleteById(toDoID);
		} else {
			throw new EntityNotFoundException();
		}
		boolean existence = tdlRepo.existsById(toDoID);	
		return !existence;
	}
	
	

}
