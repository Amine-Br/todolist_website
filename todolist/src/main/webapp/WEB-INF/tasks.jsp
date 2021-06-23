<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>tasks!</title>

    <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

	<link rel="stylesheet" href="css/bootstrap.min.css">

	
	<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script> -->
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Navbar</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav ml-auto mr-5">
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <c:out value="${ sessionScope.name }"></c:out>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="/todolist/update">Update Profile</a>
          <div class="dropdown-divider"></div>
			<form action="tasks" method="post">
				<input type="hidden" name="action" value="logout"/>
				<input type="submit" class="dropdown-item" name="logout" value="Log out"/>
    		</form>        
    	  </div>
      </li>
    </ul>
  </div>
</nav>

<div class="my-5 mx-5">
	<form method="post" action="tasks">
		<input type="hidden" name="action" value="add"/>
	  <div class="form-row">
	    <div class="col-7">
	      <input type="text" class="form-control" placeholder="Todo item" name="todo">
	    </div>
	    <div class="col">
	      <input type="date" class="form-control" placeholder="date" name="date">
	    </div>
	    <div class="col">
		  <button type="submit" class="btn btn-success"><i class="zmdi zmdi-plus"></i> add</button>    </div>
	  </div>
	</form>
</div>





<div class="table-responsive">
	<table class="table table-bordered">
	  <thead>
	    <tr>
	      <th scope="col">#</th>
	      <th scope="col">Todo item</th>
	      <th scope="col">Date</th>
	      <th scope="col">Status</th>
	      <th scope="col" colspan="3" class="text-center">Actions</th>
	    </tr>
	  </thead>
	  <tbody>
		<c:forEach var="task" items="${ tasks }" >
			<c:if test="${ task.status == 'in progress' }">
					<tr>
				      <th scope="row">${ task.id }</th>
				      <td>${ task.todo }</td>
				      <td>${ task.task_date }</td>
				      <td>${ task.status }</td>
				      <td class="text-center">
				      	<form action="tasks" method="post">
				      		<input type="hidden" name="action" value="delete">
				      		<input type="hidden" name="id" value="${ task.id }">
				      		<button type="submit" class="btn btn-danger"><i class="zmdi zmdi-delete"></i> remove</button>
				      	</form>
				      </td>
				      <td class="text-center">
				      		<button type="submit" class="btn btn-info"><i class="zmdi zmdi-edit" data-toggle="modal" data-target="#a${ task.id }"></i> edit</button>
								<div class="modal fade" id="a${ task.id }" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
								  <div class="modal-dialog modal-dialog-centered" role="document">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
								        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
								          <span aria-hidden="true">&times;</span>
								        </button>
								      </div>
								      <div class="modal-body">
										<form method="post" action="tasks">
											<input type="hidden" name="action" value="edit">
				      						<input type="hidden" name="id" value="${ task.id }">
										  <div class="form-row">
										    <div class="col-7">
										      <input type="text" class="form-control" placeholder="Todo item" name="todo" value="${ task.todo }">
										    </div>
										    <div class="col">
										      <input type="date" class="form-control" placeholder="date" name="date" value="${task.task_date }">
										    </div>
										    <div class="col">
											  <button type="submit" class="btn btn-success"><i class="zmdi zmdi-plus"></i> update</button>    </div>
										  </div>
										</form>
								      </div>
								      <div class="modal-footer">
								        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
								        <button type="button" class="btn btn-primary">Save changes</button>
								      </div>
								    </div>
								  </div>
								</div>
				      </td>
				      <td class="text-center">
				      	<form action="tasks" method="post">
				      		<input type="hidden" name="action" value="complete">
				      		<input type="hidden" name="id" value="${ task.id }">
				      		<button type="submit" class="btn btn-success"><i class="zmdi zmdi-check"></i> complete</button>
				      	</form>
				      </td>
				    </tr>
			    
			</c:if>
		  	<c:if test="${ task.status == 'finished' }">
			  	<tr style="background: #8fd19e;">
		      		<th scope="row">${ task.id }</th>
				    <td>${ task.todo }</td>
				    <td>${ task.task_date }</td>
				    <td>${ task.status }</td>
		      		<td class="text-center" colspan="3">
						<form action="tasks" method="post">
				      		<input type="hidden" name="action" value="delete">
				      		<input type="hidden" name="id" value="${ task.id }">
				      		<button type="submit" class="btn btn-danger"><i class="zmdi zmdi-delete"></i> remove</button>
				      	</form>		      		
		      		</td>
		    	</tr>
		  	</c:if>
	  	</c:forEach>
	    

	  </tbody>
	</table>
</div>    	
    	
    <script  src="js/jquery.min.js"></script>	
    <script  src="js/bootstrap.min.js"></script>
    
    
    
    
    
    
    
  
    
    
    
    
    
    
    
    
    	
</body>

</html>