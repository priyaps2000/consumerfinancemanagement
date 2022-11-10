import React,{Component} from "react";
import axios from 'axios';
 
 
export default class AdminDashboard extends Component{
 
    constructor(props) {
        super(props)
        this.state ={
            users:[],
            userCount: '',
            status: ''
        }
        this.fetchUserTable = this.fetchUserTable.bind(this);
    }
 
    componentDidMount() {
 
        axios.get("http://localhost:8080/consumerfinancemanagement/api/admin/dashboard").then((res)=>{
            this.setState({userCount: res["data"].length}, ()=>{this.setState({users: res["data"]}, ()=>{
                this.fetchUserTable()
            })});
        })
    }
 
    fetchUserTable(){
        const rows = [];
        console.log("count ", this.state.userCount)
        for (let i = 0; i < this.state.userCount; i++) {
            if(this.state.users[i]){    
                rows.push(<tr>
                    <th>{this.state.users[i]["name"]}</th>
                    <th>{this.state.users[i]["userId"]}</th>
                    <th>{this.state.users[i]["userName"]}</th>
                    <th>{this.state.users[i]["emailId"]} </th>
                    <th>{this.state.users[i]["phoneNo"]}</th>                        
                    <th>{this.state.users[i]["address"]}</th>
                    <th>{this.state.users[i]["bank"]}</th>
                    <th>{this.state.users[i]["accountNo"]}</th>                      
                    <th>{this.state.users[i]["cardType"]}</th>
                    <th>{this.state.users[i]["docUpload"]}</th>
                    <th><button>{this.state.users[i]["actvnStatus"]}</button></th>
                </tr>);
            }
        }
            return <table className="table">
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
   
         
 
    render(){
        return(
            <div>
                <h1>ADMIN DASHBOARD</h1>
                <button>Collect EMI of current month</button>
                <div className="users">
                    {this.fetchUserTable()}
                </div>
               
            </div>
        );
    }
}
