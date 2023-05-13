import './Avatar.css';
import { stringToRGB } from '../../utils/stringToRGB';
import classNames from 'classnames';

type Props = {
  userId: string;
  alt: string;
  className?: string;
};

export function Avatar(props: Props) {
  const { alt, userId, className } = props;
  const text = computeText(alt);
  const classes = classNames(className, 'avatar');
  return (
    <div
      style={{ backgroundColor: stringToRGB(userId) }}
      className={classes}
      title={alt}
    >
      <span className='avatar__initials'>{text}</span>
    </div>
  );
}

function computeText(fullname: string) {
  const [firstName, secondName] = fullname.split(' ');
  return `${firstName[0]}${secondName[0]}`;
}
