import {useContext} from 'react';
import {AppContext} from "../../context/AppContext";
import Products from "../products/Products";
import Checkout from "../checkout/Checkout";

const Layout = () => {
    const {page} = useContext(AppContext);
    return (
        <>
            {page === 'Products' && <Products />}
            {page === 'Checkout' && <Checkout />}
        </>
    )
}

export default Layout;