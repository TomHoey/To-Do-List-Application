package hateoas.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.qa.toDoList.controller.TaskController;
import com.qa.toDoList.data.models.Status;
import com.qa.toDoList.dto.TaskDTO;
import com.qa.toDoList.mappers.TaskMapper;

@Component
public class TaskDTOModelAssembler implements RepresentationModelAssembler<TaskDTO, EntityModel<TaskDTO>> {

	private TaskMapper taskMapper;

	@Autowired
	TaskDTOModelAssembler(TaskMapper taskMapper) {
		super();
		this.taskMapper = taskMapper;
	}

	@Override
	public EntityModel<TaskDTO> toModel(TaskDTO entity) {
		EntityModel<TaskDTO> entityModel = EntityModel.of(entity,
				linkTo(methodOn(TaskController.class).getTaskById(entity.getId())).withSelfRel().withTitle("GET"),
				linkTo(methodOn(TaskController.class).getAllTasks()).withRel("tasks").withTitle("GET"));

		if (entity.getStatus().equals(Status.COMPLETED)) {
			entityModel.add(
					linkTo(methodOn(TaskController.class).deleteTask(entity.getID())).withRel("delete")
							.withTitle("DELETE"),
					linkTo(methodOn(TaskController.class).updateTask(entity.getID(), taskMapper.mapToTasks(entity)))
							.withRel("Update").withTitle("PUT"));
		}

		return entityModel;

	}

}
