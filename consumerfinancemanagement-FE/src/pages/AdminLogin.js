import React,{Component} from "react";
import Navbar from "../components/Navbar";
import withNavigateHook from '../components/withNavigateHook';
import '../style/AdminLogin.css'


// import { Link } from 'react-router-dom';

import AuthenticationService from "../service/AuthenticationService";

const required = (value) => {
    if (!value) {
      return (
        <div className="invalid-feedback d-block">
          This field is required!
        </div>
      );
    }
  };

export class AdminLogin extends Component{

    constructor(props) {
        super(props)
 
        this.state = {
            username: '',
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
        if(!this.state.username)
        {
            formIsValid = false;
            error["username"] = "Please enter username";
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
    
    checkLogin(p){
        p.preventDefault();
        if(this.validateLogin()){
            let user={username:this.state.username, password:this.state.password};
            AuthenticationService.loginAdmin(user).then(response => {
                console.log("response from login BE", response["data"]);
                if(response["data"] == "success"){
                    console.log(this.state.username)
                    sessionStorage.setItem('username', this.state.username);
                    this.setState({showSuccessMessage:true})
                    this.setState({hasLoginFailed:false})
                    this.props.navigation('/admindashboard');
                }
                else{
                    this.setState({ showSuccessMessage: false })
                    this.setState({ hasLoginFailed: true })
                }
            }).catch(() => {
                console.log(this.state.username)
                this.setState({ showSuccessMessage: true })
                this.setState({ hasLoginFailed: false })
           
        });
        }
        
    }

    render(){
        return(
            <div>
                <Navbar />
                <div className="admin-login-container">
                    <div className="admin-login">
                        <h1>Admin Login</h1>
                        <form>
                        {this.state.hasLoginFailed && <div className="alert alert-warning">Invalid Credentials</div>}
                        {this.state.showSuccessMessage && <div>Login Sucessful</div>}
                        <div className = "form-group">
                            <label>Admin UserName</label> <br></br> 
                            <input type="text" name="username" className="form-control" value={this.state.username}
                            onChange={this.handleChange} validations={[required]} />
                            <div className="errorMsg">{this.state.error.username}</div>
                        </div>
                        <div className = "form-group">
                        <label>Password</label><br></br>
                        <input type="password" name="password" className="form-control" value={this.state.password}
                            onChange={this.handleChange} validations={[required]}/>
                        <div className="errorMsg">{this.state.error.password}</div>
                        </div>
                        <button className="btn btn-success admin-login-button" onClick={this.checkLogin}>Login</button>
                        {/* <button className="btn btn-success" action="/PasswordResetForm">Change Password</button> */}
                        {/* <Link to={"./ForgotPassword"}>Forgot Password</Link> */}
                        </form>
                    </div>
                </div>   
            </div>
        );
    }
   
}

export default withNavigateHook(AdminLogin);
