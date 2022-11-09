import React,{Component} from "react";
import axios from 'axios';
import '../style/CardDashboard.css';


export default class CardDashboard extends Component{

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
            products:[]
        }
        this.fetchPurchasedProduct=this.fetchPurchasedProduct.bind(this);
    }

    componentDidMount() {

        axios.get("http://localhost:8080/consumerfinancemanagement/api/user/find/"+this.state.username).then((res) => {
            this.setState({name: res["data"]["name"]})
        })

        axios.get("http://localhost:8080/consumerfinancemanagement/api/card/"+this.state.username).then((res) => {
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
                console.log("this.state.products[i]['totalAmount'] ",(this.state.products[i]));
                rows.push(<tr>
                        <th>{this.state.products[i]["productId"]}</th> 
                        <th>{this.state.products[i]["purchaseDate"].substring(0,10)}</th> 
                        <th>{this.state.products[i]["totalAmount"]} </th>
                        <th>{this.state.products[i]["amountpaid"]}</th>
                    </tr>);
            }
        }
        return <table className="table">
            <tr>
                <th>Product</th>
                <th>Purchase Date</th>
                <th>Total Amount</th>
                <th>Amount Paid</th>
            </tr>
            {rows}
            </table>
    }      

    render(){
        return(
            <div>
                <div className="navbar">
                    <div className="text">
                        <a>Products</a>
                        <div className="user">
                            <a>Hi {this.state.name}</a>
                            <a className="logout" onClick={() => sessionStorage.removeItem('username')}>Logout</a>
                        </div>
                    </div>
                </div>
                <h2 className="title">DASHBOARD</h2>
                <div className="card">
                    <div className="card-details">
                        <p>Card Number: {this.state.cardNo}</p>
                        <p>Username: {this.state.username}</p>
                        <p>Valid till: {this.state.validTill}</p>
                        <p>Card Type: {this.state.cardType}</p>
                        <p>ACTIVATED</p>
                    </div>
                </div>
                <div className="amount-details">
                    <p>TOTAL CREDIT: Rs. {this.state.cardLimit}</p>
                    <p>CREDIT USED: Rs. {this.state.creditUsed}</p>
                    <p>REMAINING CREDIT: Rs. {this.state.cardLimit - this.state.creditUsed}</p>
                </div>
                <h3>PRODUCTS PURCHASED</h3>
                {this.fetchPurchasedProduct()}
            </div>
        );
    }
}
