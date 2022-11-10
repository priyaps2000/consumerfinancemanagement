import React,{Component} from "react";
import axios from 'axios';
import '../style/AdminDashboard.css'

export default class AdminDashboard extends Component{
    constructor(props) {
        super(props)
        this.state ={
            users:[],
            userCount: '', 
            status: '',
            rerender: false
        }
        this.fetchUserTable = this.fetchUserTable.bind(this);
        this.activateUser = this.activateUser.bind(this);
        this.collectEMI = this.collectEMI.bind(this);
    }

    componentDidMount() {

        axios.get("http://localhost:8080/consumerfinancemanagement/api/admin/dashboard").then((res)=>{
            this.setState({userCount: res["data"].length}, ()=>{this.setState({users: res["data"]})});
        })
    }

    fetchUserTable(){
        const rows = [];
        console.log("count ", this.state.userCount)
        for (let i = 0; i < this.state.userCount; i++) {
            if(this.state.users[i]){    
                rows.push(<tr>
                    <td>{this.state.users[i]["name"]}</td>
                    <td>{this.state.users[i]["userId"]}</td>
                    <td>{this.state.users[i]["userName"]}</td>
                    <td>{this.state.users[i]["emailId"]} </td>
                    <td>{this.state.users[i]["phoneNo"]}</td>                        
                    <td>{this.state.users[i]["address"]}</td>
                    <td>{this.state.users[i]["bank"]}</td>
                    <td>{this.state.users[i]["accountNo"]}</td>                      
                    <td>{this.state.users[i]["cardType"]}</td> 
                    <td>{this.state.users[i]["docUpload"]}</td> 
                    <td><button className="admin-dashboard-button" data-username={this.state.users[i]["userName"]} onClick={(e)=>this.activateUser(e)}>{this.state.users[i]["actvnStatus"]}</button></td>
                </tr>);
            }
        }
            return <table className="user-table">
            <tr>
                <th>Name</th>
                <th>User ID</th>
                <th>Username</th>
                <th>Email ID</th>
                <th>Phone</th>
                <th>Address</th>
                <th>bank</th>
                <th>Account No</th>
                <th>Card Type</th>
                <th>Documents</th>
                <th>Status</th>
            </tr>
            {rows}
            </table>
        }

        activateUser(e){
            axios.post("http://localhost:8080/consumerfinancemanagement/api/admin/ActivationStatus/"+e.target.getAttribute('data-username'), "Activated", {headers: {"Content-Type": "text/plain"}})
            window.location.reload();
        }

        collectEMI(){
            axios.post("http://localhost:8080/consumerfinancemanagement/api/sale/refresh")
             console.log("YES")
        }
   
          

    render(){
        return(
            <div>
                <div className="admin-dashboard-navbar">
                    <p className="admin-tagline">Financial Solutions for your Personal Needs</p>
                    <p>Hi Admin</p>
                </div>
                <div className="admin-dashboard-container">
                    <h1>ADMIN DASHBOARD</h1>
                    <button className="admin-dashboard-button" onClick={()=> this.collectEMI()}>Collect EMI of current month</button>
                    <div className="users">
                        {this.fetchUserTable()}
                    </div>
                </div>
            </div>
        );
    }
}


