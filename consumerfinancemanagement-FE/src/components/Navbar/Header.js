import React, { Component } from 'react'
{/* <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" 
integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" 
crossorigin="anonymous"></link> */}

<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"></link>





class Header extends Component{
    render(){
        return(
            <div>
    <header>
          <nav>
          <div className='logo'><p> WELLS FARGO </p></div>
            <ul>
                <li class="active"><a href="#">Hi, Sapna</a></li>
                <li><a href="#"><button>Logout</button></a></li>
            </ul>
        </nav>
          </header>
            </div>
           
        )
    }
}

export default Header;