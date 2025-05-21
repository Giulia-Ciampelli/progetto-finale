import 'ldrs/mirage';

export default function Loader() {
    return (
        <div className='vh-100 d-flex justify-content-center align-items-center'>
            <l-mirage
                size="120"
                speed="2.5"
                color="#E8E6E3"
            ></l-mirage>
        </div>
    )
}