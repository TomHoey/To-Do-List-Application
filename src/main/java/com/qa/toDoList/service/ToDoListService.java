package com.qa.toDoList.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

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
	
	public List<ToDoListDTO> listAllLists() {
		List<ToDoList> toDo = tdlRepo.findAll();
		List<ToDoListDTO> toDoDTO = new ArrayList<ToDoListDTO>();
		toDo.forEach(toDoList -> toDoDTO.add(tdlMapper.mapToDTO(toDoList)));
		return toDoDTO;
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
		ToDoList listInDB;
		if (listOpt.isPresent()) {
			listInDB = listOpt.get();
		} else {
			throw new EntityNotFoundException();
		}
		
		listInDB.setToDoListName(listInDB.getToDoListName());
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
