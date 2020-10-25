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
  Card
} from "@material-ui/core";
import { getAllEmployees, searchByPage, getEmployeeById, deleteEmployee, saveEmployee } from "./EmployeeService";
import MemberEditorDialog from "./MemberEditorDialog";
import { Breadcrumb, ConfirmationDialog } from "egret";
import shortid from "shortid";

class EmployeeTable extends Component {
  state = {
    rowsPerPage: 10,
    page: 0,
    
    employeeList: [],
    shouldOpenEditorDialog: false,
    shouldOpenConfirmationDialog: false
  };
  constructor(props) {
    super(props);
    //this.state = {keyword: ''};
    this.handleTextChange = this.handleTextChange.bind(this);
  }
  handleTextChange(event) {
    this.setState({ value: event.target.value });
  }

  handleTextSearchChange = event => {
    this.setState({ keyword: event.target.value }, function () {
    })
  };

  handleKeyDownEnterSearch = e => {
    if (e.key === 'Enter') {
      this.setPage(0);
    }
  };
  setPage = page => {
    this.setState({ page });
  };

  setRowsPerPage = event => {
    this.setState({ rowsPerPage: event.target.value });
  };

  handleChangePage = (event, newPage) => {
    this.setPage(newPage);
  };

  handleDialogClose = () => {
    this.setState({
      shouldOpenEditorDialog: false,
      shouldOpenConfirmationDialog: false
    });
    this.updatePageData();
  };

  handleDeleteEmployee = id => {
    this.setState({
      id,
      shouldOpenConfirmationDialog: true
    });
  };

  handleConfirmationResponse = () => {
    deleteEmployee(this.state.id).then(() => {
      this.updatePageData();
      this.handleDialogClose();
    });
  };

  handleEditEmployee = employee => {
    this.setState({
      employee: employee,
      shouldOpenEditorDialog: true
    });
  };
  handleChange(event) {
    this.setState({ value: event.target.value });
  }

  componentDidMount() {
    this.updatePageData();

    
  }

  search() {
    this.setPage(0);
  }

  updatePageData = () => {
    var searchObject = {};
    searchObject.text = this.state.keyword;
    searchObject.pageIndex = this.state.page + 1;
    searchObject.pageSize = this.state.rowsPerPage;
    searchByPage(searchObject, this.state.page, this.state.rowsPerPage).then(({ data }) => {
      this.setState({ employeeList: [...data.content], totalElements: data.totalElements })
    }
    );
  };

  setPage = page => {
    this.setState({ page }, function () {
      this.updatePageData();
    })
  };

  setRowsPerPage = event => {
    this.setState({ rowsPerPage: event.target.value, page: 0 }, function () {
      this.updatePageData();
    })
  };

  handleChangePage = (event, newPage) => {
    this.setPage(newPage);
  };

  handleOKEditClose = () => {
    this.setState({
      shouldOpenEditorDialog: false,
      shouldOpenConfirmationDialog: false
    });
    this.setPage(0);
  };
 

  render() {
    let {
      rowsPerPage,
      page,
      employeeList,
      
      shouldOpenConfirmationDialog,
      shouldOpenEditorDialog
    } = this.state;
    






    return (
      <div className="m-sm-30">
        <div  className="mb-sm-30">
          <Breadcrumb routeSegments={[{ name: "Employee Table" }]} />
        </div>

        <Button
          className="mb-16"
          variant="contained"
          color="primary"
          onClick={() => this.setState({ shouldOpenEditorDialog: true })}
        >
          Thêm mới
        </Button>
        <TextField className="mb-16 mr-10" placeholder='Nhập từ khóa' type="text" value={this.state.keyword} onChange={this.handleTextSearchChange} onKeyDown={this.handleKeyDownEnterSearch} />
            <Button className="mb-16 mr-16" variant="contained" color="primary" onClick={() => this.search()}>Tìm kiếm</Button>
        <Card className="w-100 overflow-auto" elevation={6}>
          <Table className="crud-table" style={{ whiteSpace: "pre", minWidth: "750px" }}>
            <TableHead>
              <TableRow>
                <TableCell>STT</TableCell>
                <TableCell>ID</TableCell>
                <TableCell>Mã nhân viên</TableCell>
                <TableCell>Tên nhân viên</TableCell>
                <TableCell>Email</TableCell>
                <TableCell>Số điện thoại</TableCell>
                <TableCell>Tuổi</TableCell>
                
              </TableRow>
            </TableHead>
            <TableBody>
              {employeeList
                .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                .map((employee, index) => (
                  <TableRow>
                    <TableCell className="px-0" align="left">
                      {index + 1}
                    </TableCell>
                    <TableCell className="px-0" align="left">
                      {employee.id}
                    </TableCell>
                    <TableCell className="px-0">
                      {employee.code}</TableCell>
                    <TableCell className="px-0" align="left">
                      {employee.name}
                    </TableCell>
                    
                    <TableCell className="px-0" align="left">
                      {employee.email}
                    </TableCell>
                    <TableCell className="px-0" align="left">
                      {employee.phone}
                    </TableCell>
                    <TableCell className="px-0" align="left">
                      {employee.age}
                    </TableCell>
                    
                    <TableCell className="px-0 border-none">
                      <IconButton
                        onClick={() =>
                          this.setState({
                            selectId : employee.id,
                            shouldOpenEditorDialog: true
                          })
                        }
                      >
                        <Icon color="primary">edit</Icon>
                      </IconButton>
                      <IconButton onClick={() => 
                        this.handleDeleteEmployee(employee.id)}>
                        <Icon color="error">delete</Icon>
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
            count={employeeList.length}
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
              selectId={this.state.selectId}
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
    );
  }
}

export default EmployeeTable;