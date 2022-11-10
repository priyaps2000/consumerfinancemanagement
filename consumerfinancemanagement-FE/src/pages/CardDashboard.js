import React,{Component} from "react";
import axios from 'axios';
import '../style/CardDashboard.css';
import withNavigateHook from '../components/withNavigateHook';

export class CardDashboard extends Component{

    constructor(props) {
        super(props)
        this.state ={
            username: sessionStorage.getItem('username'),
            cardNo: '',
            name: '',
            validTill: '',
            cardType: '',
            cardLimit: '',
            creditUsed: '', 
            productCount: 10,
            products:[],
            status: 'ACTIVATED'
        }
        this.fetchPurchasedProduct=this.fetchPurchasedProduct.bind(this);
        this.logout = this.logout.bind(this);
        this.dashboard = this.dashboard.bind(this);
        this.Paycard = this.Paycard.bind(this);
    }

    logout(){
        sessionStorage.clear()
        this.props.navigation('/');
    }

    componentDidMount() {

        axios.get("http://localhost:8080/consumerfinancemanagement/api/user/find/"+this.state.username).then((res) => {
            this.setState({name: res["data"]["name"]})
        })

        axios.get("http://localhost:8080/consumerfinancemanagement/api/card/"+this.state.username).then((res) => {
            if(!res["data"]){
                this.setState({status: 'PENDING'})
            }
            this.setState({cardNo:res["data"]["cardNo"]})
            this.setState({cardLimit: res["data"]["cardLimit"]});
            this.setState({cardNo: res["data"]["cardNo"]});
            this.setState({cardType: res["data"]["cardType"]});
            this.setState({validTill: res["data"]["validity"]});
            this.setState({creditUsed: res["data"]["creditUsed"]});
        });

        axios.get("http://localhost:8080/consumerfinancemanagement/api/card/purchaseList/"+this.state.username).then((res)=>{
            this.setState({productCount: res["data"].length}, ()=>{this.setState({products: res["data"]}, ()=>{
                this.fetchPurchasedProduct()
            })});
        })
    }

    fetchPurchasedProduct(){
        const rows = [];
        for (let i = 0; i < this.state.productCount; i++) {
            if(this.state.products[i]){
                rows.push(<tr>
                        <td>{this.state.products[i]["productName"]}</td> 
                        <td>{this.state.products[i]["purchaseDate"].substring(0,10)}</td> 
                        <td>{this.state.products[i]["totalAmount"]} </td>
                        <td>{this.state.products[i]["amountpaid"]}</td>
                    </tr>);
            }
        }
        return <table className="purchased-table">
            <tr>
                <th>Product</th>
                <th>Purchase Date</th>
                <th>Total Amount</th>
                <th>Amount Paid</th>
            </tr>
            {rows}
            </table>
    } 

    Paycard(){
        axios.post("http://localhost:8080/consumerfinancemanagement/api/card/paydebit/" + this.state.username +"/" + this.state.creditUsed)
        window.location.reload();
    }
    
    dashboard(){
        this.props.navigation('/users/productlist');
    }
    
    productList(){

    }

    render(){
        return(
            <div>
                <div className="card-dashboard-navbar">
                
                    <div className="text">
                        <button className="card-dashboard-button" onClick={() => this.dashboard()}>Products</button>
                        <div className="user">
                            <a>Hi {this.state.name}</a>
                            <button className="card-dashboard-button logout" onClick={() => this.logout()}>Logout</button>
                        </div>
                    </div>
                </div>
                <div className="card-dashboard-container">
                <h2 className="title">DASHBOARD</h2>
                <div className="card">
                    <div className="card-details">
                        <p>Card Number: {this.state.cardNo}</p>
                        <p>Username: {this.state.username}</p>
                        <p>Valid till: {this.state.validTill}</p>
                        <p>Card Type: {this.state.cardType}</p>
                        <p>Status: {this.state.status}</p>
                    </div>
                </div>
                <div className="amount-details">
                    <p>TOTAL CREDIT: Rs. {this.state.cardLimit}/-</p>
                    <p>CREDIT USED: Rs. {this.state.creditUsed}/-</p>
                    <p>REMAINING CREDIT: Rs. {this.state.cardLimit - this.state.creditUsed}/-</p>
                </div>
                    <button className="product-button" onClick={() => this.Paycard()}>Pay Credit Card</button>
                <h2>PRODUCTS PURCHASED</h2>
                {this.fetchPurchasedProduct()}
                </div>
            </div>
        );
    }
}

export default withNavigateHook(CardDashboard);
