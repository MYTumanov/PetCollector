import classNames from 'classnames';
import { Avatar } from '../../components/Avatar';
import './UserCard.css';

export type UserCardProps = {
  userId: string;
  name: string;
  sum: number;
};

export function UserCard({ userId, name, sum }: UserCardProps) {
  const areYouDebtor = sum < 0;
  const subtitle = areYouDebtor ? 'Вы должны' : 'Вам должны';
  const sumTitle = `${Math.abs(sum)} ₽`;
  const userCardClasses = classNames('user-card', {
    ['user-card--debtor']: areYouDebtor,
  });
  return (
    <div className={userCardClasses}>
      <Avatar userId={userId} alt={name} className="user-card__avatar" />
      <div className="user-card__info">
        <span className="user-card__title">{name}</span>
        <span className={'user-card__subtitle'}>
          <span className="user-card__who-debtor">{subtitle}</span>
          <span className="user-card__sum">{sumTitle}</span>
        </span>
      </div>
    </div>
  );
}
