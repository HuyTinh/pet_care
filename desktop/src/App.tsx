import "react-toastify/dist/ReactToastify.css";
import "./assets/main.css";

import { RouterProvider } from "react-router-dom";
import { RouterHooks } from "./router";
import { ToastContainer } from "react-toastify";

function App(): JSX.Element {
  const hookRouter = RouterHooks();
  return (
    <>
      <RouterProvider router={hookRouter.router} />
      <ToastContainer
        autoClose={1000}
        closeOnClick
        toastClassName="!w-96 text-center"
        pauseOnHover={false}
        closeButton={false}
        stacked
      />
    </>
  );
}

export default App;
