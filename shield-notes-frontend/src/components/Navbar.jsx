import { Link } from "react-router-dom";



export const Navbar = () => {
    return (
        <header className="h-headerHeight z-50 text-textColor bg-headerColor shadow-md flex items-center sticky top-0">
            <nav className = "sm:px-10 px-4 flex w-full h-full items-center justify-between">
                <Link to="/">
                <h3 className="text-white text-[23px] font-mono">Shield Notes</h3>
                </Link>
            </nav>
            

        </header>
    )
}

export default Navbar;