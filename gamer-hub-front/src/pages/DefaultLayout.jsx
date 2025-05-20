import { Outlet } from "react-router-dom";

// componenti
import Header from "../components/Header.jsx";
import Footer from "../components/Footer.jsx";

export default function DefaultLayout() {
    return (
        <>
            <Header />
            <main>
                <div className="container">
                    <div className="row">
                        <Outlet />
                    </div>
                </div>
            </main>
            <Footer />
        </>
    )
}