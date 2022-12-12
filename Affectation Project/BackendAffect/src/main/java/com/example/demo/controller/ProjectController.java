package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Affectation;
import com.example.demo.model.NumberOfProjectsForEachStudent;
import com.example.demo.model.Project;
import com.example.demo.model.ProjectJava;
import com.example.demo.model.Student;
import com.example.demo.model.StudentJava;
import com.example.demo.model.StudentProject;
import com.example.demo.repository.NumberOfProjectStudentRepository;
import com.example.demo.repository.ProjectRepository ;
import com.example.demo.repository.StudentProjectRepository;
import com.example.demo.repository.StudentRepository;


@RestController
@CrossOrigin(origins="http://localhost:4200/")
@RequestMapping("/api/v2/")
public class ProjectController {
public static List<StudentJava> ls = new ArrayList<StudentJava>();
public static List<ProjectJava> lp = new ArrayList<ProjectJava>();
public static  List<StudentJava> lReserve = new ArrayList<StudentJava>();

@Autowired
private ProjectRepository ProjectRepository ;
@Autowired
private StudentRepository studentRepository ;
@Autowired
private StudentProjectRepository st ;
@Autowired
private NumberOfProjectStudentRepository  numStPr ;

//NumberOfProjectsForEachStudent CRUD
@EventListener(ApplicationReadyEvent.class)
public void AddColumnAuto() {
	int num = (int) numStPr.count();
	if(num==0) {
		NumberOfProjectsForEachStudent  numb = new NumberOfProjectsForEachStudent();
		numb.setNum(3);
		numStPr.save(numb);
	}
}
@PutMapping("/NumberOfProjectForEachSt")
public ResponseEntity<NumberOfProjectsForEachStudent> updateNumPrSt( @RequestBody NumberOfProjectsForEachStudent numPrStudent){
	NumberOfProjectsForEachStudent  numPrS = numStPr.findById(1)
.orElseThrow(() -> new ResourceNotFoundException("Project not exist with id :" + 1));

	numPrS.setNum(numPrStudent.getNum());
	NumberOfProjectsForEachStudent n = numStPr.save(numPrS);
return ResponseEntity.ok(n);
}
@GetMapping("/NumberOfProjectForEachSt")
public int  GetNumberOfPrST(){
 
	return	numStPr.findAll().get(0).getNum();
		 
}
//////////Project Crud
@GetMapping("/projects")
public List<Project> getAllProject(){
return ProjectRepository.findAll();
}

@PostMapping("/addProject")
public Project createProject(@RequestBody Project project) {
return ProjectRepository.save(project);
}
// get Project by id rest api
@GetMapping("/project/{id}")
public ResponseEntity<Project> getProjectById(@PathVariable int id) {
Project project = ProjectRepository.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("Project not exist with id :" + id));
return ResponseEntity.ok(project);
}

// update Project rest api

@PutMapping("/projectUp/{id}")
public ResponseEntity<Project> updateProject(@PathVariable Integer id, @RequestBody Project projectDetails){
Project project = ProjectRepository.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("Project not exist with id :" + id));

project.setName(projectDetails.getName());
Project p = ProjectRepository.save(project);
return ResponseEntity.ok(p);
}

// delete project rest api
@DeleteMapping("/projectDel/{id}")
@Transactional
public ResponseEntity<Map<String, Boolean>> deleteProject(@PathVariable Integer id){
Project s = ProjectRepository.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("Project not exist with id :" + id));

ProjectRepository.delete(s);
Map<String, Boolean> response = new HashMap<>();
response.put("deleted", Boolean.TRUE);
st.DeleteProjectbyId(id);
return ResponseEntity.ok(response);
}




/////Student Crud
@GetMapping("/students")
public List<Student> getAllStudent(){
return studentRepository.findAll();
}

@PostMapping("/addStudent")
public Student createStudent(@RequestBody Student student) {
return studentRepository.save(student);
}
// get student by id rest api
@GetMapping("/students/{id}")
public ResponseEntity<Student> getStudentById(@PathVariable int id) {
Student student = studentRepository.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));
return ResponseEntity.ok(student);
}

// update student rest api

@PutMapping("/studentUp/{id}")
public ResponseEntity<Student> updateStudent(@PathVariable Integer id, @RequestBody Student studentDetails){
Student student = studentRepository.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("Student does not exist with id :" + id));

student.setMoyen(studentDetails.getMoyen());
student.setNom(studentDetails.getNom());


Student s = studentRepository.save(student);
return ResponseEntity.ok(s);
}

// delete Student rest api
@DeleteMapping("/studentDel/{id}")
public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable Integer id){
Student s = studentRepository.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));

studentRepository.delete(s);
Map<String, Boolean> response = new HashMap<>();
response.put("deleted", Boolean.TRUE);
return ResponseEntity.ok(response);
}


/////Student Project Controller


@GetMapping("/studentProjects")
public List<StudentProject> getAllLp(){
return st.findAll();
}

@PostMapping(value="/addStudentProject")
public StudentProject createStudentProject(@RequestBody StudentProject sp) {
List<ProjectJava>  Lp=ProjectsByStudentId(sp.getStudent());
if(Lp.size()>= GetNumberOfPrST()) { return null;}
List<StudentProject> le=st.findAll();
for(StudentProject s :le) {
if(s.getProject()==sp.getProject()&& s.getStudent()==sp.getStudent()) {
return null;
}
}
return st.save(sp);
}

@GetMapping("/studentProjects/{id}")
public List<StudentProject> getAllLpByStudentId(@PathVariable int id) {

return getAllProjectByStudentId(id);

}


@GetMapping("/JavaStudentProjects/{id}")
public StudentJava getAllJavaLpByStudentId(@PathVariable int id) {
List<StudentProject> le =getAllProjectByStudentId(id);
List<ProjectJava> listJava = new ArrayList<ProjectJava>();
for (StudentProject spp: le ) {
Project project = ProjectRepository.findById(spp.getProject())
.orElseThrow(() -> new ResourceNotFoundException("Project not exist with id :" + id));
ProjectJava p1 = new ProjectJava(project.getName());
listJava.add(p1);
}

Student student = studentRepository.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));
StudentJava stu= new StudentJava();

stu.setNom(student.getNom());
                stu.setMoyen(student.getMoyen());
                stu.setListP(listJava);
return stu;
}


@GetMapping("/ProjectsByStudentId/{id}")
public List<ProjectJava> ProjectsByStudentId(@PathVariable int id) {
List<StudentProject> le =getAllProjectByStudentId(id);
List<ProjectJava> listJava = new ArrayList<ProjectJava>();
for (StudentProject spp: le ) {
Project project = ProjectRepository.findById(spp.getProject())
.orElseThrow(() -> new ResourceNotFoundException("Project not exist with id :" + id));
ProjectJava p1 = new ProjectJava(project.getName());
p1.setId(spp.getProject());
listJava.add(p1);
}

Student student = studentRepository.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));
StudentJava stu= new StudentJava();

stu.setNom(student.getNom());
                stu.setMoyen(student.getMoyen());
                stu.setListP(listJava);
return stu.getListP();
}
/// the rest of student list of choices
@GetMapping("/AvailableStudentProject/{id}")
public List<ProjectJava> TheRestOfProjectOfStudent(@PathVariable int id) {
List<ProjectJava>  Lp=ProjectsByStudentId(id);
List<Project> AllProject=  getAllProject();
List<ProjectJava>  lJavall=new ArrayList<ProjectJava> ();
                for (int i =0;i<AllProject.size();i++) {
                ProjectJava jpp = new ProjectJava();
                jpp.setId(AllProject.get(i).getId());
                jpp.setName(AllProject.get(i).getName());
                lJavall.add(jpp);
                }
List<ProjectJava>  RestProject=new ArrayList<ProjectJava> ();

                for(int i=0;i<lJavall.size();i++) {
                Boolean x = true;
                for(int j=0;j<Lp.size();j++) {
                if(lJavall.get(i).getId()==Lp.get(j).getId()) {
                x=false;
                }
                }
                if(x==true) { RestProject.add(lJavall.get(i));}
                }


return RestProject;
}

@GetMapping("/DeleteStudentProject")
@Transactional
public void DeleteStudentProject(@RequestParam int id_student, @RequestParam int id_project) {
st.DeleteProject( id_student,  id_project);
//System.out.println(id_student+ "/////"+  id_project);

}




/// Element needed For Local Affectation  
@GetMapping("/JavaProjects")
public List<ProjectJava> getAllJavaProject(){
	List<Project> lp= ProjectRepository.findAll();
	List<ProjectJava> lpnew = new ArrayList<ProjectJava>();

	for (Project p: lp) {
		ProjectJava p1= new ProjectJava();
		p1.setId(p.getId());
		p1.setName(p.getName());
		lpnew.add(p1);
	}
	return lpnew;
} 

public List<StudentProject> getAllProjectByStudentId(int id){
	List<StudentProject> ls = st.findAll();
	List<StudentProject> lnew = new ArrayList<StudentProject>();

	for (StudentProject s: ls) {
		if(s.getStudent()==id) {
			lnew.add(s);
		}
	}
	return lnew;
}
@GetMapping("/JavaStudentProjects")
public List<StudentJava> getAllJavaLStudent() {
	List<StudentJava>  lJava = new ArrayList<StudentJava>();
	List <Student> ls = studentRepository.findAll();
	for (int i= 0;i<ls.size();i++) {
		
	int id= ls.get(i).getId();
	List<StudentProject> le =getAllProjectByStudentId(id);
	List<ProjectJava> listJava = new ArrayList<ProjectJava>();
	for (StudentProject spp: le ) {
		Project project = ProjectRepository.findById(spp.getProject())
				.orElseThrow(() -> new ResourceNotFoundException("Project not exist with id :" + id));
		ProjectJava p1 = new ProjectJava();
		p1.setId(spp.getProject());
		
		p1.setName(getProjectNameById(spp.getProject()));
		listJava.add(p1);
		
	}
	
	Student student = studentRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));
	StudentJava stu= new StudentJava();
    stu.setId(id);
	stu.setNom(student.getNom());
    stu.setMoyen(student.getMoyen());
    stu.setListP(listJava);
    lJava.add(stu);
	}
	return lJava;
}

public String getProjectNameById(int id) {
	
	List<Project>  lp=ProjectRepository.findAll();
	for (Project p : lp) {
		if(p.getId()==id) {return p.getName();}
	}
	return "Invalid Project";
}


/// Affectation  !!!!!!! Keep in mind that the number of prjects must be equal to number of students!!!!!!!!!
@GetMapping("/AffectationList")
public List<Affectation> getStudentAffectation (){
	 List<Affectation> la = new ArrayList<Affectation>();

ls=getAllJavaLStudent();
lp=getAllJavaProject();


do {
int pos=  getBestStudentNumber()  ; 
int validProject =findIdProject( pos);
if(validProject!=-1) {
 Affectation a = new Affectation(ls.get(pos).getNom(),ls.get(pos).getListP().get(validProject).getName());
 la.add(a);
 int idProject =  ls.get(pos).getListP().get(validProject).getId();
 removeProjectbyId(idProject);
}
else {
 lReserve.add(ls.get(pos))  ;
}
ls.remove(pos);
}
while(ls.size()>0);

do {
for (int i=0;i<lp.size();i++) {
Affectation a = new Affectation(lReserve.get(i).getNom(),lp.get(i).getName());
la.add(a);
lp.remove(i);
lReserve.remove(i);
}
}while(lp.size()>0);

return la;
}


public  static StudentJava  getBestStudent() {
double max=0;
int counter=0;
for(int i=0; i< ls.size();i++) {
if(ls.get(i).getMoyen()>=max) {
max= ls.get(i).getMoyen();	
counter=i;
}
}
return ls.get(counter);	
}

public  static int  getBestStudentNumber() {
double max=0;
int counter=0;
for(int i=0; i< ls.size();i++) {
if(ls.get(i).getMoyen()>=max) {
max= ls.get(i).getMoyen();	
counter=i;

}
}
return counter;	
}
public static int findIdProject(int pos) {
for (int i=0;i<ls.get(pos).getListP().size();i++ ){
for (int j = 0;j<lp.size();j++) {
if(ls.get(pos).getListP().get(i).getId()==lp.get(j).getId())
{
	return i;
}
}
}
return -1;
}
public static void removeProjectbyId(int id) {
for (int i=0; i< lp.size();i++) {
if (lp.get(i).getId()==id) {lp.remove(i);}
}
}


}