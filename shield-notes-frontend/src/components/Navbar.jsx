import { Link } from "react-router-dom";
import {useState} from 'react'
import { IoMdMenu } from "react-icons/io";
import { AiOutlineMenuUnfold } from "react-icons/ai";



export const Navbar = () => {
    const [mobileToggle , setMobileToggle] = useState(false)
    return (
    
        <header className="h-headerHeight z-50 text-textColor bg-headerColor shadow-md flex items-center sticky top-0">
            <nav className = "sm:px-10 px-4 flex w-full h-full items-center justify-between">
                <Link to="/">
                <h3 className="text-white text-[23px] font-mono">Shield Notes</h3>
                </Link>
            </nav>
            <ul className= {`lg:static  absolute left-0  top-16 w-full lg:w-fit lg:px-0 sm:px-10 px-4  lg:bg-transparent bg-headerColor   ${
            mobileToggle
              ? "min-h-fit max-h-navbarHeight lg:py-0 py-4 shadow-md shadow-slate-700 lg:shadow-none bg-white"
              : "h-0 overflow-hidden "
          }  lg:h-auto transition-all duration-100 font-montserrat text-textColor flex lg:flex-row flex-col lg:gap-8 gap-2`}>
    
            
            </ul>
            
            <span onClick={() => setMobileToggle(!mobileToggle)} className="lg:hidden block cursor-pointer text-textColor  shadow-md hover:text-slate-400 mr-2">
                {mobileToggle ?
                (
                <AiOutlineMenuUnfold className="text-2xl text-white"/>):
                (<IoMdMenu className="text-2xl text-white"/>)
                
            }
            </span>

        </header>
    )
}

export default Navbar;