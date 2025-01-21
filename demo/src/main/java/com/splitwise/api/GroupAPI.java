package com.splitwise.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.splitwise.DTO.GroupsDTO;
import com.splitwise.service.GroupService;

@RestController
@CrossOrigin
@Validated
public class GroupAPI {

	
	@Autowired
	private GroupService groupService;
	
	
	@PostMapping("/create_group")
    public ResponseEntity<String> createGroup(@RequestBody GroupsDTO group) {
		String createdGroup = groupService.createGroup(group);
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
    }
	
	
	
}
