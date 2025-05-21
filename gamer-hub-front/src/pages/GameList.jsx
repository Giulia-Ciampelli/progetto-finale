import { useContext } from "react";
import { Link } from "react-router-dom";

// context
import APIContext from "../context/APIContext.jsx";

// componenti
import Loader from "../components/Loader.jsx";

// custom hooks
import usePageTitle from "../hooks/PageTitle.jsx";

export default function GameList() {
    const { games, loading } = useContext(APIContext);
    usePageTitle("GamerHub - Games");

    return (
        <>
            <div className="row row-cols-2 row-cols-md-3 row-cols-lg-4 g-4 justify-content-center mx-0">
                {loading ? <Loader /> :
                    games.map((game) => (
                        <div className="col" key={game.id}>
                            <Link to={`/games/${game.id}`} className="text-decoration-none">
                                <div className="card align-items-center px-4 bg-card">
                                    <p className="card-title fw-bold py-2 txt-primary">
                                        {game.name}
                                    </p>
                                    <img className="img-fluid same-height" src={game.url} alt={game.name} />
                                    <p className="card-text py-2 txt-primary">
                                        Price: {game.price}€
                                    </p>
                                </div>
                            </Link>
                        </div>
                    ))
                }
            </div>
        </>
    )
}