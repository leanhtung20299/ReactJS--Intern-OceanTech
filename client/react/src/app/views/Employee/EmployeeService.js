import axios from "axios";

export const getAllEmployees = () => {
  //return axios.get("/api/user/all");
  //alert( axios.defaults.headers.common["Authorization"]);
  return axios.get("http://localhost:8092/sample/api/employee");  
}; 

export const getAllEmployees2 = () => {
  return axios.get("http://localhost:8092/sample/api/employee/getdataaftersave");  
}; 

export const getAllEmployeesListsize = () => {
  return axios.get("http://localhost:8092/sample/api/employee/lengthlist");  
}; 

export const getAllEmployeesfrist = (page,rowpage) => {
  return axios.get("http://localhost:8092/sample/api/employee/"+page+"/"+rowpage);  
}; 
// http://localhost:8092/sample/api/animal/1/1
export const getUserById = (uid) => {
  return axios.get("http://localhost:8092/sample/api/employee/" +uid );
};
export const deleteUser = (id) => {
  return axios.delete("http://localhost:8092/sample/api/employee/" +id);
}; 
export const addNewUser = (User) => {
  return axios.post("http://localhost:8092/sample/api/employee/save", User);
};
export const updateUser = (User) => {
  return axios.put("http://localhost:8092/sample/api/employee/"+ User.id,User);
};
export const search = (key,page,rowpage) => {
  return axios.get("http://localhost:8092/sample/api/employee/search/"+ key+"/"+page+"/"+rowpage);
};
export const searchlistsize = (key) => {
  return axios.get("http://localhost:8092/sample/api/employee/search/"+ key);
};
export const exportexcelAPI  = (key) => {
  // return axios.get("http://localhost:8092/sample/api/animal/Excel");
  if(key=="")
  return axios({
    method: 'GET',
    url : 'http://localhost:8092/sample/api/employee/Excel',
    responseType:'blob',
    headers:{
      'Content-Type': 'application/octet-stream'
    },
  })
  else{
    return axios({
      method: 'GET',
      url : 'http://localhost:8092/sample/api/employee/Excel/'+key,
      responseType:'blob',
      headers:{
        'Content-Type': 'application/octet-stream'
      },
    })
  }
};
export const exportexcelReport = () => {
  return axios({
    method: 'GET',
    url : 'http://localhost:8092/sample/api/ReportController/Excel',
    responseType:'blob',
    headers:{
      'Content-Type': 'application/octet-stream'
    },
  })
};