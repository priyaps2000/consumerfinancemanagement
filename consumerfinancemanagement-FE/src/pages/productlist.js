import React,{Component} from "react";
import axios from 'axios';
import '../style/ProductList.css';

export default class ProductList extends Component{

    constructor(props) {
        super(props)
        this.state ={
            username:  sessionStorage.getItem('username'),
            name:'',
            productList: [],
            productCount: ''
        }
        this.fetchProductList=this.fetchProductList.bind(this);
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
                            <button data-product-id={this.state.productList[i]["productId"]} onClick={(e) => console.log("click on product ID: ", e.target.getAttribute('data-product-id'))}>Buy Now</button>
                        </div>
                    </div>);
            }
        }
        return <div className="product-container">{rows}</div>
    }      

    render(){
        return(
            <div>
                <div className="navbar">
                    <div className="text">
                        <a>Dashboard</a>
                        <div className="user">
                            <a>Hi {this.state.name}</a>
                            <a className="logout" onClick={() => sessionStorage.removeItem('username')}>Logout</a>
                        </div>
                    </div>
                </div>
                {this.fetchProductList()}
            </div>
        );
    }
}
   