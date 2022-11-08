import React from 'react';
import {
Nav,
NavLink,
Bars,
NavMenu,
NavBtn,
NavBtnLink,
} from './NavbarElements';

const Navbar = () => {
return (
	<>
	<Nav>
		<Bars />

		<NavMenu>
		<NavLink to='/login' activeStyle>
			Login
		</NavLink>
		
		<NavLink to='/sign-up' activeStyle>
			Register
		</NavLink>
		<NavLink to='/users' activeStyle>
			Users
		</NavLink>
		</NavMenu>
	</Nav>
	</>
);
};

export default Navbar;
