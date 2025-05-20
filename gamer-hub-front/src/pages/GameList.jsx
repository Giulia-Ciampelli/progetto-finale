import { useContext } from "react";
import { Link } from "react-router-dom";

// context
import APIContext from "../context/APIContext.jsx";

// componenti
import Loader from "../components/Loader.jsx";

export default function GameList() {
    const { games, loading } = useContext(APIContext);

    return (
        <>
            <div className="row row-cols-2 row-cols-md-3 row-cols-lg-4 g-4 justify-content-center mx-0">
                {loading ? <Loader /> :
                    (
                        <div className="col">
                            {games.map((game) => (
                                <Link to={`/games/${game.id}`} key={game.id} className="text-decoration-none">
                                    <div className="card bg-card" game={game}>
                                        <p className="txt-primary">
                                            Name: {game.name}
                                        </p>
                                        <p className="txt-primary">
                                            Price: {game.price}€
                                        </p>
                                        <img src={game.url} alt={game.name} />
                                    </div>
                                </Link>
                            ))}
                        </div>
                    )}
            </div>
        </>
    )
}