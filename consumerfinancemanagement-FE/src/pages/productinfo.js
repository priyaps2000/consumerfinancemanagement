import React,{Component} from "react";
import axios from 'axios';
import '../style/register.css'
import '../style/ProductList.css';
import withNavigateHook from '../components/withNavigateHook';
import UserService from "../service/UserService";

export class Productinfo extends Component{

    constructor(props) {
        super(props)
        this.state ={
            username:  sessionStorage.getItem('username'),
            name:'',
            productid: sessionStorage.getItem('ProductId'),
            productName: '',
            productDetails: '',
            productCost: '',
            productURL : '',
            productPurchaseCount : '',
            EMI : '',
            response : "",
            error:{}
        }
        this.purchaseProduct=this.purchaseProduct.bind(this);
    }

    handleChange(e) {
        // this.state.EMI =  e.target.value;
        this.setState({
            EMI : e.target.value
        });
    }

    logout(){
        sessionStorage.clear()
        this.props.navigation('/login');
    }

    dashboard(){
        this.props.navigation('/users/cardDashboard');
    }

    componentDidMount() {

        axios.get("http://localhost:8080/consumerfinancemanagement/api/user/find/"+this.state.username).then((res) => {
            this.setState({name: res["data"]["name"]})
        })

        axios.get("http://localhost:8080/consumerfinancemanagement/api/product/getProduct/" + this.state.productid).then((res)=>{
            this.setState({productName: res["data"]["productName"]});
            this.setState({productDetails: res["data"]["productDetails"]});
            this.setState({productCost: res["data"]["cost"]});
            this.setState({productURL: res["data"]["productURL"]});
            this.setState({productPurchaseCount: res["data"]["purchaseCount"]});
        })

        axios.post("http://localhost:8080/consumerfinancemanagement/api/sale/buyNow/" + this.state.username + "/" + this.state.productid + "/" + this.state.EMI).then((res)=>{
            this.setState({response: res["data"]});
        })
    }

    validateEMI(){
        let error = {};
        let formIsValid = true;
        if(!this.state.EMI)
        {
            formIsValid = false;
            error["reply"] = "Please Choose the EMI Option";
        }
        this.setState({
            error: error
        });
        return formIsValid;
    }

    purchaseProduct(){
        if(this.validateEMI()){
            let user={username:this.state.username, productid:this.state.productid, EMI:this.state.EMI};
            UserService.purchaseProduct(user).then(response => {
                this.state.res = response["data"]
                console.log("response from Sales BE", response["data"]);
                console.log(this.state.EMI)
                let error = {};
                error["reply"] = this.state.res;
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

    // fetchProduct(){
    //     const rows = [];
    //     for (let i = 0; i < this.state.productCount; i++) {
    //         if(this.state.productList[i]){
    //             rows.push(<div className="product">
    //                     <img className="product-img" src={this.state.productList[i]["productURL"]}></img>
    //                     <div className="product-details">
    //                         <p className="product-name">{this.state.productList[i]["productName"]}</p>
    //                         <p>Product details: {this.state.productList[i]["productDetails"]}</p>
    //                         <p>Cost: Rs. {this.state.productList[i]["cost"]}/-</p>
    //                         <button data-product-id={this.state.productList[i]["productId"]} onClick={(e) => console.log("click on product ID: ", e.target.getAttribute('data-product-id'))}>Buy Now</button>
    //                     </div>
    //                 </div>);
    //         }
    //     }
    //     return <div className="product-container">{rows}</div>
    // }      

    render(){
        return(
            <div>
                <div className="navbar">
                    <div className="text">
                        <button className="logout" onClick={() => this.dashboard()}>CardDashboard</button>
                        <div className="user">
                            <a>Hi {this.state.name}</a>
                            <button className="logout" onClick={() => this.logout()}>Logout</button>
                        </div>
                    </div>
                </div>

                <div className="product">
                    <img className="product-img" src={this.state.productURL}></img>
                    <div className="product-details">
                        <p className="product-name">{this.state.productName}</p>
                        <p>Product details: {this.state.productDetails}</p>
                        <p>Cost: Rs. {this.state.productCost}/-</p>
                    </div>
                </div>
                
                <div className = "Container">
                    <label> Slect EMI Option: </label>
                    <select name="card" className="form-control" 
                        value={this.state.EMI} onChange={(e) => this.setState({EMI : e.target.value})}>
                            <option>Select</option>
                            <option>1</option>
                            <option>3</option>
                            <option>6</option>
                    </select>
                    <label> Months</label>
                    <br></br>
                <button onClick={() => this.purchaseProduct()}>Buy Now</button>
                    <div className="errorMsg">{this.state.error.reply}</div>
                </div>

                {/* {this.fetchProductList()} */}
            </div>
        );
    }
}

export default withNavigateHook(Productinfo);