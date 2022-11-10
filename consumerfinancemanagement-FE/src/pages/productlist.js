import React,{Component} from "react";
import axios from 'axios';
import '../style/ProductList.css';
import withNavigateHook from '../components/withNavigateHook';
import { NavLink as Link } from 'react-router-dom';

export class ProductList extends Component{

    constructor(props) {
        super(props)
        this.state ={
            username:  sessionStorage.getItem('username'),
            name:'',
            productList: [],
            productCount: ''
        }
        this.fetchProductList=this.fetchProductList.bind(this);
        this.gotoProductinfo = this.gotoProductinfo.bind(this);
    }

    logout(){
        sessionStorage.clear()
        this.props.navigation('/');
    }

    dashboard(){
        this.props.navigation('/users/cardDashboard');
    }

    componentDidMount() {

        axios.get("http://localhost:8080/consumerfinancemanagement/api/user/find/"+this.state.username).then((res) => {
            this.setState({name: res["data"]["name"]})
        })

        axios.get("http://localhost:8080/consumerfinancemanagement/api/product/productDashboard").then((res)=>{
            this.setState({productCount: res["data"].length}, ()=>{this.setState({productList: res["data"]}, ()=>{
                this.fetchProductList()
            })});
        })
    }

    gotoProductinfo(e) {
        sessionStorage.setItem("ProductId", e.target.getAttribute('data-product-id'))
        this.props.navigation('/users/productlist/productinfo')
    }

    fetchProductList(){
        const rows = [];
        for (let i = 0; i < this.state.productCount; i++) {
            if(this.state.productList[i]){
                rows.push(<div className="product">
                        <img className="product-img" src={this.state.productList[i]["productURL"]}></img>
                        <div className="product-details">
                            <p className="product-name">{this.state.productList[i]["productName"]}</p>
                            <p>Product details: {this.state.productList[i]["productDetails"]}</p>
                            <p>Cost: Rs. {this.state.productList[i]["cost"]}/-</p>
                            <button className="productlist-button" data-product-id={this.state.productList[i]["productId"]} onClick={(e) => {this.gotoProductinfo(e)}}>Buy Now</button>
                        </div>
                    </div>);
            }
        }
        return <div className="product-container">{rows}</div>
    }      

    render(){
        return(
            <div>
                <div className="productlist-navbar">
                    <div className="productlist-text">
                        <button className="logout productlist-button" onClick={() => this.dashboard()}>CardDashboard</button>
                        <div className="user">
                            <a>Hi {this.state.name}</a>
                            <button className="logout productlist-button" onClick={() => this.logout()}>Logout</button>
                        </div>
                    </div>
                </div>
                {this.fetchProductList()}
            </div>
        );
    }
}

export default withNavigateHook(ProductList);