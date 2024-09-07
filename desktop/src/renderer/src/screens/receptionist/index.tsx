import { Link } from 'react-router-dom'

export const ReceptionistScreen = () => {
  return (
    <div>
      Receptionist Screen
      <Link to={'/'} className="btn btn-outline">
        Auth Screen
      </Link>
    </div>
  )
}
