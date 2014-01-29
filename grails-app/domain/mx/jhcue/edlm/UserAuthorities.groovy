package mx.jhcue.edlm

import org.apache.commons.lang.builder.HashCodeBuilder

class UserAuthorities implements Serializable {

	private static final long serialVersionUID = 1

	User user
	Authorities authorities

	boolean equals(other) {
		if (!(other instanceof UserAuthorities)) {
			return false
		}

		other.user?.id == user?.id &&
			other.authorities?.id == authorities?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (user) builder.append(user.id)
		if (authorities) builder.append(authorities.id)
		builder.toHashCode()
	}

	static UserAuthorities get(long userId, long authoritiesId) {
		UserAuthorities.where {
			user == User.load(userId) &&
			authorities == Authorities.load(authoritiesId)
		}.get()
	}

	static UserAuthorities create(User user, Authorities authorities, boolean flush = false) {
		new UserAuthorities(user: user, authorities: authorities).save(flush: flush, insert: true)
	}

	static boolean remove(User u, Authorities r, boolean flush = false) {

		int rowCount = UserAuthorities.where {
			user == User.load(u.id) &&
			authorities == Authorities.load(r.id)
		}.deleteAll()

		rowCount > 0
	}

	static void removeAll(User u) {
		UserAuthorities.where {
			user == User.load(u.id)
		}.deleteAll()
	}

	static void removeAll(Authorities r) {
		UserAuthorities.where {
			authorities == Authorities.load(r.id)
		}.deleteAll()
	}

	static mapping = {
		id composite: ['authorities', 'user']
		version false
	}
}
