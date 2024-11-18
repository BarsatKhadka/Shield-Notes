/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {
      height: {
        headerHeight: "74px"
      }, 
      colors:{
        textColor: "#fffff",
        headerColor: "#070b2e"
      }
    },
  },
  plugins: [],
}
