import React, { Component } from "react";
import {
  Dialog,
  Button,
  Grid,
  FormControlLabel,
  Switch
} from "@material-ui/core";
import { ValidatorForm, TextValidator } from "react-material-ui-form-validator";
import { getUserById, updateUser, addNewUser } from "./EmployeeService";
import { generateRandomId } from "utils";

class MemberEditorDialog extends Component {
  state = {
    id: "",
    code: "",
    email: "",
    phone: "",
    age: "",
    name: "",
  };

  handleChange = (event, source) => {
    event.persist();
    if (source === "switch") {
      this.setState({ isActive: event.target.checked });
      return;
    }

    this.setState({
      [event.target.name]: event.target.value
    });
  };

  handleFormSubmit = () => {
    let { uid } = this.props;
    // alert(id);
    if (uid) {
      updateUser({
        id: this.state.id,
        email: this.state.email,
        code: this.state.code,
        phone: this.state.phone,
        age: this.state.age,
        name : this.state.name,
      }).then(() => {
        this.props.handleClose();
      });
    } else {
      sessionStorage.setItem("statussave",1);
      addNewUser({
        email: this.state.email,
        code: this.state.code,
        phone: this.state.phone,
        age: this.state.age,
        name : this.state.name,
      }).then(() => {
        this.props.handleClose();
      });
    }
  };

  componentWillMount() {
    getUserById(this.props.uid).then(response => {
      this.setState({
        id: response.data.id,
        email: response.data.email,
        code: response.data.code,
        phone: response.data.phone,
        age: response.data.age,
        name : response.data.name,
      })
    }
    );
  }
  render() {
    let {
      email,
      code,
      phone,
      age,
      name,
    } = this.state;
    let { open, handleClose } = this.props;

    return (
      <Dialog onClose={handleClose} open={open}>
        <div className="p-24">
          <h4 className="mb-20">Update Member</h4>
          <ValidatorForm ref="form" onSubmit={this.handleFormSubmit}>
            <Grid className="mb-16" container spacing={4}>
              <Grid item sm={12} xs={12}>

                <TextValidator
                  className="w-100 mb-16"
                  label="email"
                  onChange={this.handleChange}
                  type="text"
                  name="email"
                  value={email}
                  validators={["required"]}
                  errorMessages={["this field is required"]}
                />
                <TextValidator
                  className="w-100 mb-16"
                  label="name"
                  onChange={this.handleChange}
                  type="text"
                  name="name"
                  value={name}
                  validators={["required"]}
                  errorMessages={["this field is required"]}
                />

                <TextValidator
                  className="w-100 mb-16"
                  label="code"
                  onChange={this.handleChange}
                  type="text"
                  name="code"
                  value={code}
                  validators={["required"]}
                  errorMessages={["this field is required"]}
                />

                <TextValidator
                  className="w-100 mb-16"
                  label="phone"
                  onChange={this.handleChange}
                  type="text"
                  name="phone"
                  value={phone}
                  validators={["required"]}
                  errorMessages={["this field is required"]}
                />
                <TextValidator
                  className="w-100 mb-16"
                  label="age"
                  onChange={this.handleChange}
                  type="text"
                  name="age"
                  value={age}
                  validators={["required"]}
                  errorMessages={["this field is required"]}
                />
              </Grid>

            </Grid>

            <div className="flex flex-space-between flex-middle">
              <Button variant="contained" color="primary" type="submit">
                Save
              </Button>
              <Button onClick={() => this.props.handleClose()}>Cancel</Button>
            </div>
          </ValidatorForm>
        </div>
      </Dialog>
    );
  }
}

export default MemberEditorDialog;
