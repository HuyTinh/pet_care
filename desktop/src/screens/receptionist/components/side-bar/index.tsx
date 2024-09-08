import { NavLink, useNavigate } from "react-router-dom";
import { CiLogout, CiViewTable } from "react-icons/ci";
import { CiCalendar, CiCircleInfo } from "react-icons/ci";
import { ReactElement } from "react";
import { toast } from "react-toastify";

interface MenuItem {
  title: string;
  icon: ReactElement;
}

const menuItems: MenuItem[] = [
  {
    title: "Appointment",
    icon: <CiCalendar size={32} />,
  },
  {
    title: "Guide",
    icon: <CiCircleInfo size={32} />,
  },
];

export const SideBar = () => {
  const navigate = useNavigate();

  const useToast = () => {
    toast.success("Log out successful!!!", {
      position: "top-center",
    });

    setTimeout(() => {
      navigate("/");
    }, 500);
  };

  return (
    <div className="h-full w-56 overflow-hidden flex flex-col px-4">
      <div className="bg-white rounded-xl flex p-2">
        <div className="avatar">
          <div className="mask mask-squircle w-12">
            <img
              src="https://th.bing.com/th/id/OIP.L5yCQIs4iE6WKHXZs8-j1QAAAA?rs=1&pid=ImgDetMain"
              alt="a"
            />
          </div>
        </div>
      </div>
      <div className="flex-1">
        <div className="pt-5 space-y-5">
          {menuItems.map((item, index) => (
            <NavLink to={"/"} key={index} className="block">
              <div
                className="text-white outline outline-2 outline-white
                hover:bg-white hover:text-black
                hover:outline-black hover:scale-110 transition
                px-2 py-2 rounded-xl w-[14rem] flex items-center gap-x-2"
              >
                {item.icon}
                <span className="pt-[0.2rem]">{item.title}</span>
              </div>
            </NavLink>
          ))}
        </div>
      </div>
      <div className="bg-white rounded-xl p-2 ">
        <button className="btn btn-error rounded-xl !p-2" onClick={useToast}>
          <CiLogout size={24} />
        </button>
      </div>
    </div>
  );
};
