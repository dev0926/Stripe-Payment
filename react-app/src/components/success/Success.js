import {useContext, useEffect} from 'react';
import {AppContext} from "../../context/AppContext";
import styles from '../../styles/Success.module.css';
import {useNavigate} from 'react-router-dom';

const Success = () => {
    const {cart, paymentRef, orderId} = useContext(AppContext);
    const navigate = useNavigate();
    useEffect(() => {
        if(cart.length === 0) navigate("/");
    }, [cart, navigate]);
    return <>
        <h1>Success!</h1>
        <div className={styles.imagesContainer}>
            { cart.length &&
                cart.map((product) =>
                    <div key={product.id} className={styles.successImgContainer}>
                        <img className={styles.successImg} src={product.photoUrl} alt={product.name} />
                    </div>
                )
             }
        </div>
        <p>Your order ID is {orderId}</p>
        <p>Your payment ref is {paymentRef}</p>
    </>;
}

export default Success;