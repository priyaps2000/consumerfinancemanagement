import React,{Component} from "react";
import Navbar from "../components/Navbar";
import withNavigateHook from '../components/withNavigateHook';
import '../style/ForgotPassword.css'

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

export class ForgotPassword extends Component{

    constructor(props) {
        super(props)
 
        this.state = {
            emailid: '',
            token: '',
            password: '',
            cpassword: '',
            hasLoginFailed: false,
            showSuccessMessage: false,
            error: {},
            res: ''
        }
        this.handleChange=this.handleChange.bind(this);
        this.sendOTP=this.sendOTP.bind(this);
        this.verifyOTP = this.verifyOTP.bind(this);
    }

    handleChange(event) {
        this.setState(
            {
                [event.target.name]
                    : event.target.value
            }
        )
    }
    
    validateSendOTP(){
        let error = {};
        let fields = this.state;
        let formIsValid = true;

        if (!fields["emailid"]) {
            formIsValid = false;
            error["emailid"] = "*Please enter your email-ID.";
          }

        if (typeof fields["emailid"] !== "undefined") {
            //regular expression for email validation
          var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
           
            if (!pattern.test(fields["emailid"])) {
              formIsValid = false;
              error["emailid"] = "*Please enter valid email-ID.";
            }
          }
        this.setState({
            error: error
          });
          return formIsValid;
    }
    
    sendOTP(p){
        p.preventDefault();
        if(this.validateSendOTP()){
            let user={emailid:this.state.emailid};
            AuthenticationService.sendOTP(user).then(response => {
                this.state.res = response["data"]
                let error = {};
                error["emailid"] = this.state.res;
                this.setState({
                    error: error
                });
            }).catch(() => {
                console.log(this.state.username)
                this.setState({ showSuccessMessage: true })
                this.setState({ hasLoginFailed: false })
           
        });
        }
    }

    validateVerfiyLogin(){
        let error = {};
        let fields = this.state;
        let formIsValid = true;
        if(!this.state.token)
        {
            formIsValid = false;
            error["token"] = "Please enter the token";
        }
        if (!fields["password"]) {
            formIsValid = false;
            error["password"] = "*Please enter your password.";
        }
        if (!fields["cpassword"]) {
            formIsValid = false;
            error["cpassword"] = "*Please enter password to confirm.";
        }

        if (fields.password !== fields.cpassword) {
            formIsValid = false;
            error["cpassword"] = "*Please enter same password.";    
        }
        this.setState({
            error: error
          });
          return formIsValid;
    }


    verifyOTP(p){
        p.preventDefault();
        if(this.validateVerfiyLogin()){
            let user={token:this.state.token, password:this.state.password, cpassword:this.state.cpassword};
            AuthenticationService.verifyOTP(user).then(response => {
                this.state.res = response["data"]
                console.log("response from Password BE", response["data"]);
                console.log(this.state.res)
                // if(response["data"] == "success"){
                //     console.log(this.state.username)
                //     sessionStorage.setItem('username', this.state.username);
                //     this.setState({showSuccessMessage:true})
                //     this.setState({hasLoginFailed:false})
                //     this.props.navigation('/admindashboard');
                // }
                let error = {};
                error["cpassword"] = this.state.res;
                this.setState({
                    error: error
                });
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
                <div className="forgot-password-container">
                    <div className="forgot-password">
                    <h1>Forgot Password</h1>
                        <form>
                        {this.state.hasLoginFailed && <div className="alert alert-warning">Invalid Credentials</div>}
                        <div className = "form-group">
                            <label>User Email-ID:</label>  
                            <input type="text" name="emailid" className="form-control" value={this.state.emailid}
                            onChange={this.handleChange} validations={[required]} />
                            <div className="errorMsg">{this.state.error.emailid}</div>
                        </div>
                        <button className="btn btn-success forgot-button" onClick={this.sendOTP}>Send OTP</button>

                        <div className = "form-group">
                        <label>OTP:</label>
                        <input type="password" name="token" className="form-control" value={this.state.token}
                            onChange={this.handleChange} validations={[required]}/>
                        <div className="errorMsg">{this.state.error.token}</div>
                        </div><br></br>
                        <div className = "form-group">
                        <label>New Password:</label>
                        <input type="password" name="password" className="form-control" value={this.state.password}
                            onChange={this.handleChange} validations={[required]}/>
                            <div className="errorMsg">{this.state.error.password}</div>
                        </div>
                        <div className = "form-group">
                        <label>Confirm Password:</label>
                        <input type="password" name="cpassword" className="form-control" value={this.state.cpassword}
                            onChange={this.handleChange} validations={[required]}/>
                        <div className="errorMsg">{this.state.error.cpassword}</div>
                        </div>
                        <button className="btn btn-success forgot-button" onClick={this.verifyOTP}>Verify OTP and Change Password</button>
                        {/* <button className="btn btn-success" action="/PasswordResetForm">Change Password</button> */}
                        {/* <Link to={"./ForgotPassword"}>Forgot Password</Link> */}
                        
                        </form>
                    </div>
                </div>      
            </div>
        );
    }
   
}

export default withNavigateHook(ForgotPassword);
