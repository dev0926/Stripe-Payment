import { useState, useEffect, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import Product from "../product/Product";
import {AppContext} from "../../context/AppContext";
import styles from '../../styles/Products.module.css';

const Products = () => {
    const [products, setProducts] = useState([]);
    const {cart} = useContext(AppContext);
    const navigate = useNavigate();

    useEffect(() => {
        axios.get('http://localhost:8080/api/products').then((res) => {
            setProducts(res.data);
        }).catch(err => console.log(err));
    }, []);

    const onGoToCheckout = () => {
        if(cart.length)
            navigate('/checkout');
        else
            alert("Select Products");
    }

    return (
        <>
            <div className={styles.productsHeadline}>
                <h1>Select a Product:</h1>
                <div>
                    <button className={styles.checkOutBtn} onClick={onGoToCheckout} >Go to Checkout ➤</button>
                </div>
            </div>
            <div className={styles.productList}>
                {products.map((product) =>
                    <Product key={product.id} product={product} />
                )}
            </div>
        </>
    );
}

export default Products;