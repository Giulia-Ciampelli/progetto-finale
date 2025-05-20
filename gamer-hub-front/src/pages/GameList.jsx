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
            {loading ? <Loader /> :
            (
                <div className="col">
                    {games.map((game) => (
                        <Link to={`/games/${game.id}`} key={game.id}>
                            <div className="card" game={game}>
                                <p>
                                    Name: {game.name}
                                </p>
                                <p>
                                    Price: {game.price}
                                </p>
                                <img src={game.url} alt={game.name} />
                            </div>
                        </Link>
                    ))}
                </div>
            )}
        </>
    )
}