import React,{Component} from "react";
import { Link, Routes, Route } from "react-router-dom";
import withNavigateHook from '../components/withNavigateHook';
 

import AuthenticationService from "../service/AuthenticationService";
import { login } from "@userfront/core";

const required = (value) => {
    if (!value) {
      return (
        <div className="invalid-feedback d-block">
          This field is required!
        </div>
      );
    }
  };

export class Login extends Component{    
    constructor(props) {
        super(props)
 
        this.state = {
            email: '',
            password: '',
            hasLoginFailed: false,
            showSuccessMessage: false,
            error: {}
        }
        this.handleChange=this.handleChange.bind(this);
        this.checkLogin=this.checkLogin.bind(this);
    }

    handleChange(event) {
        this.setState(
            {
                [event.target.name]
                    : event.target.value
            }
        )
    }

    validateLogin(){
        let error = {};
        let formIsValid = true;
        if(!this.state.email)
        {
            formIsValid = false;
            error["email"] = "Please enter username";
        }
        if(!this.state.password)
        {
            formIsValid = false;
            error["password"] = "Please enter password";
        }
        this.setState({
            error: error
          });
          return formIsValid;
    }

    // PublicRoute = (auth) => {
    //     if (auth) {
    //       return <Navigate replace to="/users" />;
    //     }
    //   };

    
    checkLogin(p){
        p.preventDefault();
        if(this.validateLogin()){
            let user={email:this.state.email, password:this.state.password};
            AuthenticationService.loginUser(user).then(response => {
                console.log("response from login BE", response["data"]);
                if(response["data"] === "success"){
                    let auth = true;
                    console.log(this.state.email)
                    sessionStorage.setItem('username', this.state.email);
                    this.props.navigation('/users/productlist');
                }
            }).catch(() => {
                this.setState({ showSuccessMessage: true })
                this.setState({ hasLoginFailed: false })
           
        });
        }
        
    }

    render(){
        return(
            <div>
                <h1>User Login</h1>
                <div className="container">
                    <form>
                    {this.state.hasLoginFailed && <div className="alert alert-warning">Invalid Credentials</div>}
                    {this.state.showSuccessMessage && <div>Login Sucessful</div>}
                    <div className = "form-group">
                        <label>User Name:</label>  
                        <input type="text" name="email" className="form-control" value={this.state.email}
                        onChange={this.handleChange} validations={[required]} />
                        <div className="errorMsg">{this.state.error.email}</div>
                    </div>
                    <div className = "form-group">
                    <label>Password:</label>
                    <input type="password" name="password" className="form-control" value={this.state.password}
                        onChange={this.handleChange} validations={[required]}/>
                    <div className="errorMsg">{this.state.error.password}</div>
                    </div>
                    <button className="btn btn-success" onClick={this.checkLogin}>Login</button>
                    <button className="btn btn-success" 
                    action="/PasswordResetForm"
                    >Change Password</button>
                    <Link to={"forgotpassword"}>Forgot Password</Link><br></br>
                    <Link to={"adminlogin"}>Are you an Admin ? Login Here</Link>
                    
                    </form>
                </div>
            </div>
        );
    }
   
}

export default withNavigateHook(Login);
