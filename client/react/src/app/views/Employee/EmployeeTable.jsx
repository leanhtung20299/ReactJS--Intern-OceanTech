import React, { Component } from "react";
import {
  IconButton,
  Table,
  TableHead,
  TableBody,
  TextField,
  TableRow,
  TableCell,
  Icon,
  TablePagination,
  Button,
  Card,
  Grid,
  Paper
} from "@material-ui/core";
import { getAllEmployees, deleteUser, search, exportexcelAPI, getAllEmployees2, getAllEmployeesfrist, getAllEmployeesListsize, searchlistsize ,exportexcelReport} from "./EmployeeService";
import MemberEditorDialog from "./MemberEditorDialog";
import { Breadcrumb, ConfirmationDialog } from "egret";
import shortid from "shortid";
class EmployeeTable extends Component {
  state = {
    rowsPerPage: 10,
    page: 0,
    userList: [],
    shouldOpenEditorDialog: false,
    shouldOpenConfirmationDialog: false,
    key: "",
  };

  setRowsPerPage = (event) => {
    this.setState({
      rowsPerPage: event.target.value,
      page: 0,
    }, function () {
      this.updatePageData();
    });

  };

  setPage = (newPage) => {
    this.setState({ page: newPage }, function () {
      this.updatePageData();
    });
  };

  handleChangePage = (event, newPage) => {
    this.setPage(newPage);

  };


  handleDialogClose = () => {
    this.setState({
      shouldOpenEditorDialog: false,
      shouldOpenConfirmationDialog: false
    }, function () {
      this.updatePageData();
    });

  };

  handleEditEmployee = (employee) => {
    this.setState({
      employee: employee,
      shouldOpenEditorDialog: true
    });
  };

  handleOKEditClose = () => {
    this.setState({
      shouldOpenEditorDialog: false,
      shouldOpenConfirmationDialog: false
    });
    this.setPage(0);
  };

  handleDeleteUser = (id) => {
    this.setState({
      id: id,
      shouldOpenConfirmationDialog: true
    });
  };

  search = () => {
    if (this.state.key == "") {
      alert("Không tìm thấy kết quả");
      this.setState({ key: "" });
      this.updatePageData();
    }
    else {
      search(this.state.key, 0, 10).then(({ data }) => {
        if (data.list.length > 0) {
          this.setState({
            userList: [...data.list],
          })
          searchlistsize(this.state.key).then((response) => {
            this.setState({
              lengthlist: response.data.length,
              page: 0,
              rowsPerPage: 10,
            })
          });
        }
        else {
          alert("Không tìm thấy kết quả");
          this.setState({ key: "" });
          this.updatePageData();
        }
      }
      );
    }
    // this.setState({key:""});
  };
  handleConfirmationResponse = () => {
    deleteUser(this.state.id).then(() => {
      // alert(this.state.lengthlist);
      // alert(this.state.page);

      if (this.state.key == "") {
        getAllEmployeesListsize().then(response => {
          this.setState({
            lengthlist: response.data.length,
          }, function () {
            if (this.state.page > 0 && this.state.lengthlist % this.state.rowsPerPage == 0 && this.state.lengthlist>0) {
              this.setState({ page: this.state.page - 1 }, function () {
                this.handleDialogClose();
              });
            } else {
              this.handleDialogClose();
            }
          })
        });
      } else {
        searchlistsize(this.state.key).then((response) => {
          this.setState({
            lengthlist: response.data.length,
          }, function () {
            if (this.state.page > 0 && this.state.lengthlist % this.state.rowsPerPage == 0) {
              this.setState({ page: this.state.page - 1 }, function () {
                this.handleDialogClose();
              });
            } else {
              this.handleDialogClose();
            } 
          })
        });
      }
    
    });


  };
  handleTextSearchChange = (event) => {
    var valuereturn = event.target.value;
    valuereturn = valuereturn.replace(/\s+/g, '');
    if (valuereturn.length > 0)
      this.setState({ key: event.target.value })
    else
      this.setState({ key: "" })
  }

  componentDidMount() {
    getAllEmployeesfrist(this.state.page, this.state.rowsPerPage).then(({ data }) => {
      this.setState({
        userList: [...data.list],
      })
    }
    );
    this.getListSize();

  }
  getListSize = () => {
    getAllEmployeesListsize().then(response => {
      this.setState({
        lengthlist: response.data.length,
      })
    }
    );
  }
  updatePageData = () => {
    if (this.state.key == "")
      this.getListSize();
    if (sessionStorage.getItem("statussave")) {
      getAllEmployees2(0, 10).then(({ data }) => {
        this.setState({
          userList: [...data.list],
          rowsPerPage: 10,
          page: 0,
          key: "",
        }, function () {
          sessionStorage.removeItem("statussave");
        });
        this.getListSize();
      }
      );
    }
    else {
      if (this.state.key == "") {
        getAllEmployeesfrist(this.state.page, this.state.rowsPerPage).then(({ data }) => {
          this.setState({
            userList: [...data.list],
          })
        });
      }
      else {
        search(this.state.key, this.state.page, this.state.rowsPerPage).then(({ data }) => {
          if (data.list.length > 0) {
            this.setState({
              userList: [...data.list],
            })
          }
          else if(data.list.length==0){
            alert("Kết quả trống");
            this.setState({
              userList: [...data.list],
            })
          }
          else {
            alert("Không tìm thấy kết quả");
            this.updatePageData();
          }
          // alert(this.state.lengthlist);
          // alert(this.state.row);
          // alert(this.state.rowsPerPage);
        })
      }
    }
  };
  exportexcel = () => {
    exportexcelAPI(this.state.key).then((response) => {
      const url = window.URL.createObjectURL(new Blob([response.data]));
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', 'excel.xlsx'); //or any other extension
      document.body.appendChild(link);
      link.click();
    })
      .catch(err => {
        console.log("AXIOS ERROR: ", err);
      });
  }
  exportexcelReportInfomation = () =>{
    exportexcelReport().then((response) => {
      const url = window.URL.createObjectURL(new Blob([response.data]));
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', 'excel.xlsx'); //or any other extension
      document.body.appendChild(link);
      link.click();
    })
      .catch(err => {
        console.log("AXIOS ERROR: ", err);
      });
  }
  render() {
    let {
      rowsPerPage,
      page,
      userList,
      shouldOpenConfirmationDialog,
      shouldOpenEditorDialog
    } = this.state;
    var stt = rowsPerPage * page + 1;
    return (
      <div>
        <div className="m-sm-30">
          <div className="mb-sm-30">
            <Breadcrumb routeSegments={[{ name: "Employee Table" }]} />
          </div>
          <Grid container spacing={1}>
            
            <Grid item xs={2}>
              <Paper >
                <Button
                  className="mb-16"
                  variant="contained"
                  color="primary"
                  onClick={() =>
                    this.setState({
                      shouldOpenEditorDialog: true,
                      uid: null,
                    })
                  }
                >
                  Thêm mới thành viên
             </Button>
              </Paper>
            </Grid>

            <Grid item xs={2}>
              <Paper >
                <div className="list-group" >
                  <TextField className="mb-16 mr-10" placeholder='Nhập từ khóa' type="text" onChange={this.handleTextSearchChange} onKeyDown={this.handleKeyDownEnterSearch} />
                  <Button className="mb-16 mr-16" variant="contained" color="primary" onClick={() => this.search()}>Tìm kiếm</Button>
                </div>
              </Paper>
            </Grid>

            <Grid item xs={2}>
              <Paper >
                <Button

                  className="mb-16"
                  variant="contained"
                  color="primary"
                  onClick={() => this.exportexcel()}
                >
                  Xuất file Excel
                </Button>
              </Paper>
            </Grid>

            <Grid item xs={2}>
              <Paper >
                <Button

                  className="mb-16"
                  variant="contained"
                  color="primary"
                  onClick={() => this.exportexcelReportInfomation()}
                >
                  Xuất file Excel InfomationReportDto 
                </Button>
              </Paper>
            </Grid>
          </Grid>

          <Card className="w-100 overflow-auto" elevation={7}>
            <Table className="crud-table" style={{ whiteSpace: "pre", minWidth: "750px" }}>
              <TableHead>
                <TableRow>
                  <TableCell>STT</TableCell>
                  <TableCell>ID</TableCell>
                  <TableCell>Name</TableCell>
                  <TableCell>Code</TableCell>
                  <TableCell>Email</TableCell>
                  <TableCell>Phone</TableCell>
                  <TableCell>Age</TableCell>
                  <TableCell></TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {userList
                  // .slice(page *rowsPerPage, page * rowsPerPage + rowsPerPage)
                  .map((user, index) => (
                    <TableRow>
                      <TableCell className="px-0" align="left">
                        {stt++}
                      </TableCell>
                      <TableCell className="px-0" align="left">
                        {user.id}
                      </TableCell>
                      <TableCell className="px-0" align="left">
                        {user.name}
                      </TableCell>
                      <TableCell className="px-0" align="left">
                        {user.code}
                      </TableCell>
                      <TableCell className="px-0" align="left">
                        {user.email}
                      </TableCell>
                      <TableCell className="px-0" align="left">
                        {user.phone}
                      </TableCell>
                      <TableCell className="px-0" align="left">
                        {user.age}
                      </TableCell>
                      {/* <TableCell className="px-0">
                      {user.isActive ? (
                        <small className="border-radius-4 bg-primary text-white px-8 py-2 ">
                          active
                        </small>
                      ) : (
                        <small className="border-radius-4 bg-light-gray px-8 py-2 ">
                          inactive
                        </small>
                      )}
                    </TableCell> */}
                      <TableCell className="px-0 border-none">
                        <IconButton
                          onClick={() =>
                            this.setState({
                              uid: user.id,
                              shouldOpenEditorDialog: true
                            })
                          }
                        >
                          <Icon color="primary"> Sửa </Icon>
                        </IconButton>
                        <IconButton
                          onClick={() => this.handleDeleteUser(user.id)}>
                          <Icon color="error"> Xóa </Icon>
                        </IconButton>
                      </TableCell>
                    </TableRow>
                  ))}
              </TableBody>
            </Table>

            <TablePagination
              className="px-16"
              rowsPerPageOptions={[5, 10, 25]}
              component="div"
              count={this.state.lengthlist}
              rowsPerPage={rowsPerPage}
              page={page}
              backIconButtonProps={{
                "aria-label": "Previous Page"
              }}
              nextIconButtonProps={{
                "aria-label": "Next Page"
              }}
              onChangePage={this.handleChangePage}
              onChangeRowsPerPage={this.setRowsPerPage}
            />

            {shouldOpenEditorDialog && (
              <MemberEditorDialog
                handleClose={this.handleDialogClose}
                open={shouldOpenEditorDialog}
                uid={this.state.uid}
              />
            )}

            {shouldOpenConfirmationDialog && (
              <ConfirmationDialog
                open={shouldOpenConfirmationDialog}
                onConfirmDialogClose={this.handleDialogClose}
                onYesClick={this.handleConfirmationResponse}
                text="Are you sure to delete?"
              />
            )}
          </Card>
        </div>
      </div>
    );
  }
}

export default EmployeeTable;
