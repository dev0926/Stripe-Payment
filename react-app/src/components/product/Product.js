import styles from '../../styles/Products.module.css';
import { useContext } from 'react'
import {AppContext} from "../../context/AppContext";

const Product = ({product}) => {
    const {cart, setCart} = useContext(AppContext);

    const isProductInCart = cart.some(cartItem => cartItem.id === product.id);

    const onSelectProduct = () => {
        setCart((prevCart) => {
            const isProductInCart = prevCart.some(cartItem => cartItem.id === product.id);

            if (isProductInCart) {
                return prevCart.filter(cartItem => cartItem.id !== product.id);
            } else {
                return [...prevCart, product];
            }
        });
    }

    return (
        <div className={`${styles.product} ${isProductInCart ? styles.productHighlighted : ''}`} onClick={onSelectProduct} >
            <div className={styles.productImageContainer}>
                <img className={styles.productImage} src={product.photoUrl} alt={product.name} />
            </div>
            <div className={styles.productHeadline}>
                <div className={styles.productTitle}>{product.name}</div>
                <div className={styles.productPrice}>{product.price}</div>
            </div>
            <div className={styles.productDescription}>{product.description}</div>
        </div>
    )
}

export default Product;