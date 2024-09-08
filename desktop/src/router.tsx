import { createBrowserRouter } from "react-router-dom";
import { RootLayout } from "./components/layout/root-layout";
import { AuthScreen } from "./screens/auth";
import { ReceptionistScreen } from "./screens/receptionist";
import { DoctorScreen } from "./screens/doctor";

const ClientScreen = {
  receptionist: <ReceptionistScreen />,
  doctor: <DoctorScreen />,
};

export const RouterHooks = () => {
  const role = "receptionist";

  const router = createBrowserRouter([
    {
      path: "/",
      element: <RootLayout />,
      children: [
        {
          index: true,
          element: <AuthScreen />,
        },
        {
          path: "/client",
          element: ClientScreen[role],
        },
      ],
    },
  ]);
  return { router: router };
};
