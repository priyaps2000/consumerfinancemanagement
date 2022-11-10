import React from 'react';
import { NavLink as Link } from 'react-router-dom';
import 'D:/Java Bootcamp WF/consumerfinancemanagement/consumerfinancemanagement-FE/src/style/Navbar.css';

const Navbar = () => {
return (
	<div className='global-navbar'>
		<div className='company-name'>
			<p>Five Star Personal Finance Limited</p>
		</div>
		<div className='user-elements'>
			<Link className="link" to='/login' activeStyle>Login</Link>
			<Link className="link" to='/sign-up' activeStyle>Register</Link>
		</div>
	</div>
);
};

export default Navbar;
